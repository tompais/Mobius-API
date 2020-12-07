package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.notifications.redis.messages.UploadFileMessage
import com.coder_rangers.mobius_api.notifications.redis.publishers.MessagePublisher
import com.coder_rangers.mobius_api.responses.SaveImageResponse
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.utils.ImageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ImageService @Autowired constructor(
    @Qualifier("uploadFileToS3Publisher")
    private val uploadFileToS3Publisher: MessagePublisher<UploadFileMessage>,

    private val amazonS3Service: IAmazonS3Service
) : IImageService {
    override fun saveImage(bytes: ByteArray): SaveImageResponse {
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

    override fun getImage(fileName: String): ByteArray {
        ImageUtils.assertThatIsAPNG(fileName)

        return amazonS3Service.getFileFromS3(buildFilePath(fileName))
    }

    private fun buildFilePath(fileName: String): String = "drawings/$fileName"

    private fun buildFileName(): String = "${UUID.randomUUID().toString().replace("-", "").toUpperCase()}.png"
}
