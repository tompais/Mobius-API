package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.ImagePatientAnswer
import com.coder_rangers.mobius_api.models.PatientAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import com.coder_rangers.mobius_api.services.interfaces.ITaskService
import com.coder_rangers.mobius_api.utils.ImageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.util.Base64
import javax.imageio.ImageIO

@Component
class DrawingGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService,
    private val taskService: ITaskService,
    private val imageService: IImageService
) : BaseGameAnswersResolver<String>(taskResultService) {
    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>, answers: Set<Answer>?): Int {
        val task = taskService.getTaskById(patientTaskAnswersRequest.taskId)

        val originalImageName = task.game!!.resources!!.first().fileName

        val originalImage =
            ImageIO.read(ResourceUtils.getURL("classpath:static/images/$originalImageName").openStream())

        val drawnImageInBytes = patientTaskAnswersRequest.patientAnswersRequest.first().let {
            Base64.getDecoder().decode(it)
        }.also {
            ImageUtils.assertThatIsAPNG(it)
        }

        val drawnImage = ImageIO.read(drawnImageInBytes.inputStream())

        val differencePercentage = ImageUtils.getImageDifferenceInPercent(originalImage, drawnImage)

        return (differencePercentage > 70.0).toInt()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>): List<PatientAnswer> =
        patientTaskAnswersRequest.patientAnswersRequest.map { patientAnswerRequest ->
            ImagePatientAnswer(
                imageName = imageService.saveImage(Base64.getDecoder().decode(patientAnswerRequest)).fileName
            )
        }
}
