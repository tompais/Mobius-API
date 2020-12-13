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

@Component
class DrawingGameAnswersResolver @Autowired constructor(
    taskResultService: ITaskResultService,
    private val imageService: IImageService
) : BaseGameAnswersResolver<String>(taskResultService) {
    private companion object {
        private const val THRESHOLD = 0.8
    }

    override fun getScore(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>, answers: Set<Answer>?): Int {
        val originalImageName = answers!!.map { it as ImageAnswer }.first().imageName

        val originalImageInBase64 =
            ResourceUtils.getURL("${CLASSPATH_URL_PREFIX}static/images/$originalImageName").openStream().readAllBytes()
                .let {
                    ImageUtils.assertThatIsAPNG(it)

                    Base64.getEncoder().encodeToString(it)
                }

        val drawnImageInBase64 = patientTaskAnswersRequest.patientAnswersRequest.first().also {
            ImageUtils.assertThatIsAPNG(Base64.getDecoder().decode(it))
        }

        return (imageService.compareImages(originalImageInBase64, drawnImageInBase64) <= THRESHOLD).toInt()
    }

    override fun transformToPatientAnswers(patientTaskAnswersRequest: PatientTaskAnswersRequest<String>): List<Answer> =
        patientTaskAnswersRequest.patientAnswersRequest.map { patientAnswerRequest ->
            ImageAnswer(
                imageName = imageService.uploadImageToS3(Base64.getDecoder().decode(patientAnswerRequest)).fileName,
                type = PATIENT
            )
        }
}
