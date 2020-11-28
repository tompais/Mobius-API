package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.responses.SaveImageResponse
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import com.coder_rangers.mobius_api.utils.ImageUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ImageService @Autowired constructor(
    private val amazonS3Service: IAmazonS3Service
) : IImageService {
    override fun saveImage(bytes: ByteArray): SaveImageResponse {
        ImageUtils.assertThatIsAPNG(bytes)

        val fileName = buildFileName()
        val filePath = buildFilePath(fileName)

        amazonS3Service.uploadFileToS3(filePath, bytes)

        return SaveImageResponse(fileName)
    }

    private fun buildFilePath(fileName: String): String = "drawings/$fileName"

    private fun buildFileName(): String = "${UUID.randomUUID().toString().replace("-", "").toUpperCase()}.png"
}
