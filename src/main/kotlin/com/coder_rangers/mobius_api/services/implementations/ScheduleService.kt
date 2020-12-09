package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.dto.WeeklyReportDTO
import com.coder_rangers.mobius_api.emails.services.interfaces.IEmailService
import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.models.Patient
import com.coder_rangers.mobius_api.models.Task
import com.coder_rangers.mobius_api.requests.ChartRequest
import com.coder_rangers.mobius_api.requests.ChartRequest.Data
import com.coder_rangers.mobius_api.requests.ChartRequest.Data.Dataset
import com.coder_rangers.mobius_api.requests.ChartRequest.Type
import com.coder_rangers.mobius_api.requests.ChartRequest.Type.BAR
import com.coder_rangers.mobius_api.requests.ChartRequest.Type.PIE
import com.coder_rangers.mobius_api.services.interfaces.IPatientService
import com.coder_rangers.mobius_api.services.interfaces.IScheduleService
import com.coder_rangers.mobius_api.utils.mapToCategoriesWithPercentages
import com.fasterxml.jackson.databind.ObjectMapper
import io.quickchart.QuickChart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Base64

@Service
class ScheduleService @Autowired constructor(
    private val patientService: IPatientService,

    private val emailService: IEmailService,

    @Qualifier("camelCase")
    private val mapper: ObjectMapper
) : IScheduleService {
    override fun sendWeeklyReports() {
        patientService.getActivePatientsWithFinishedTest().forEach {
            buildAndSendWeeklyReport(it)
        }
    }

    private fun buildAndSendWeeklyReport(patient: Patient) {
        buildWeeklyReport(patient.getNotTestTaskResults())?.let { weeklyReport ->
            sendWeeklyReport(patient.guardians.map { guardian -> guardian.email }.toSet(), weeklyReport)
        }
    }

    private fun sendWeeklyReport(
        guardianEmails: Set<String>,
        weeklyReport: WeeklyReportDTO
    ) {
        emailService.sendWeeklyReports(
            guardianEmails,
            weeklyReport
        )
    }

    private fun buildWeeklyReport(notTestTaskResults: List<Task.Result>): WeeklyReportDTO? {
        val lastWeekResults =
            notTestTaskResults.filter { taskResult -> taskResult.playedDate >= LocalDate.now().minusWeeks(1) }
                .sortedBy { it.task.game!!.category }

        return if (lastWeekResults.isNotEmpty()) {
            val lastWeekCategoriesWithPercentages =
                lastWeekResults.mapToCategoriesWithPercentages()

            val barChartRequest = buildChartRequest(BAR, lastWeekCategoriesWithPercentages, "Semana pasada")

            val previousWeekResults = notTestTaskResults.filter { taskResult ->
                taskResult.playedDate < LocalDate.now().minusWeeks(1) && taskResult.playedDate >= LocalDate.now()
                    .minusWeeks(2)
            }

            val hasPreviousWeekResults = previousWeekResults.isNotEmpty()

            if (hasPreviousWeekResults) {
                val previousWeekCategoriesWithPercentages =
                    previousWeekResults.mapToCategoriesWithPercentages()

                barChartRequest.data.datasets.add(
                    Dataset(
                        label = "Semana anterior",
                        data = previousWeekCategoriesWithPercentages.map { it.second }
                    )
                )
            }

            val barChartImage = getChartImageInBase64(barChartRequest)

            val generalCategoriesWithPercentages =
                notTestTaskResults.mapToCategoriesWithPercentages()

            val pieChartRequest = buildChartRequest(PIE, generalCategoriesWithPercentages)

            val pieChartImage = getChartImageInBase64(pieChartRequest)

            WeeklyReportDTO(
                barChart = barChartImage,
                pieChart = pieChartImage,
                hasVersusBarChart = hasPreviousWeekResults
            )
        } else
            null
    }

    private fun getChartImageInBase64(chartRequest: ChartRequest): String =
        QuickChart().apply {
            config = mapper.writeValueAsString(chartRequest)
        }.toByteArray().let {
            Base64.getEncoder().encodeToString(it)
        }

    private fun buildChartRequest(
        type: Type,
        categoriesWithPercentages: List<Pair<Category, Int>>,
        label: String? = null
    ) = ChartRequest(
        type = type,
        data = Data(
            labels = categoriesWithPercentages.map { it.first.displayName }.toSet(),
            datasets = mutableListOf(
                Dataset(
                    label = label,
                    data = categoriesWithPercentages.map { it.second }
                )
            )
        )
    )
}
