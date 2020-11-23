package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.responses.UploadImageResponse
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class ImageService @Autowired constructor(
    private val amazonS3Service: IAmazonS3Service
) : IImageService {
    override fun saveImage(imageFile: MultipartFile): UploadImageResponse {
        if (!isPng(imageFile)) {
            throw IllegalImageExtensionException()
        }

        val fileName = buildFileName()
        val filePath = buildFilePath(fileName)

        amazonS3Service.uploadFileToS3(filePath, imageFile.bytes)

        return UploadImageResponse(fileName)
    }

    private fun isPng(imageFile: MultipartFile) =
        "png".equals(FilenameUtils.getExtension(imageFile.originalFilename), ignoreCase = true)

    private fun buildFilePath(fileName: String): String = "drawings/$fileName"

    private fun buildFileName(): String = "${UUID.randomUUID().toString().replace("-", "").toUpperCase()}.png"
}
