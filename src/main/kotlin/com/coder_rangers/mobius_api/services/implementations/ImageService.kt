package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.notifications.redis.messages.UploadFileMessage
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.responses.SaveImageResponse
import com.coder_rangers.mobius_api.responses.imagga.CompareResponse
import com.coder_rangers.mobius_api.responses.imagga.UploadResponse
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.utils.ImageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType.MULTIPART_FORM_DATA
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import java.util.UUID

@Service
class ImageService @Autowired constructor(
    @Qualifier("uploadFileToS3Publisher")
    private val uploadFileToS3Publisher: MessagePublisher<UploadFileMessage>,

    private val amazonS3Service: IAmazonS3Service,

    private val imaggaWebClient: WebClient
) : IImageService {
    override fun uploadImageToS3(bytes: ByteArray): SaveImageResponse {
        ImageUtils.assertThatIsAPNG(bytes)

        val fileName = buildFileName()
        val filePath = buildFilePath(fileName)

        uploadFileToS3Publisher.publish(
            UploadFileMessage(
                filePath,
                bytes
            )
        )

        return SaveImageResponse(fileName)
    }

    override fun getImageFromS3(fileName: String): ByteArray {
        ImageUtils.assertThatIsAPNG(fileName)

        return amazonS3Service.getFileFromS3(buildFilePath(fileName))
    }

    override fun compareImages(originalImageInBase64: String, drawnImageInBase64: String): Double =
        uploadImageToImagga(originalImageInBase64).result!!.uploadId.let { originalImageUploadId ->
            uploadImageToImagga(drawnImageInBase64).result!!.uploadId.let { drawnImageUploadId ->
                compareImagesWithImagga(originalImageUploadId, drawnImageUploadId).result!!.distance
            }
        }

    private fun compareImagesWithImagga(originalImageUploadId: String, drawnImageUploadId: String): CompareResponse {
        return imaggaWebClient.get()
            .uri { uriBuilder ->
                uriBuilder.path("/images-similarity/categories/general_v3")
                    .queryParam("image_upload_id", originalImageUploadId)
                    .queryParam("image2_upload_id", drawnImageUploadId)
                    .build()
            }
            .retrieve()
            .bodyToMono(CompareResponse::class.java)
            .block()!!
    }

    private fun uploadImageToImagga(imageInBase64: String): UploadResponse {
        return imaggaWebClient.post()
            .uri("/uploads")
            .contentType(MULTIPART_FORM_DATA)
            .contentLength(imageInBase64.length.toLong())
            .body(
                BodyInserters.fromFormData("image_base64", imageInBase64)
            )
            .retrieve()
            .bodyToMono(UploadResponse::class.java)
            .block()!!
    }

    private fun buildFilePath(fileName: String): String = "drawings/$fileName"

    private fun buildFileName(): String = "${UUID.randomUUID().toString().replace("-", "").toUpperCase()}.png"
}
