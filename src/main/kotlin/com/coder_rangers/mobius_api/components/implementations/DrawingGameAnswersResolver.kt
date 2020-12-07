package com.coder_rangers.mobius_api.components.implementations

import com.coder_rangers.mobius_api.models.Answer
import com.coder_rangers.mobius_api.models.Answer.Type.PATIENT
import com.coder_rangers.mobius_api.models.ImageAnswer
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.services.interfaces.ITaskResultService
import com.coder_rangers.mobius_api.utils.ImageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX
import java.util.Base64
import javax.imageio.ImageIO

@Component
class DrawingGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService,
    private val imageService: IImageService
) : BaseGameAnswersResolver<String>(taskResultService) {
    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>, answers: Set<Answer>?): Int {
        val originalImageName = answers!!.map { it as ImageAnswer }.first().imageName

        val originalImage =
            ImageIO.read(ResourceUtils.getURL("${CLASSPATH_URL_PREFIX}static/images/$originalImageName").openStream())

        val drawnImageInBytes = patientTaskAnswersRequest.patientAnswersRequest.first().let {
            Base64.getDecoder().decode(it)
        }.also {
            ImageUtils.assertThatIsAPNG(it)
        }

        val drawnImage = ImageIO.read(drawnImageInBytes.inputStream())

        val differencePercentage = ImageUtils.getImageDifferenceInPercent(originalImage, drawnImage)

        return (differencePercentage > 70.0).toInt()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>): List<Answer> =
        patientTaskAnswersRequest.patientAnswersRequest.map { patientAnswerRequest ->
            ImageAnswer(
                imageName = imageService.saveImage(Base64.getDecoder().decode(patientAnswerRequest)).fileName,
                type = PATIENT
            )
        }
}
