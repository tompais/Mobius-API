package com.coder_rangers.mobius_api.services.implementations

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.responses.UploadImageResponse
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.util.UUID

@Service
class ImageService : IImageService {
    override fun saveImage(imageFile: MultipartFile): UploadImageResponse {
        if (!isPng(imageFile)) {
            throw IllegalImageExtensionException()
        }

        val fileName = buildFileName()
        val filePath = buildFilePath(fileName)

        FileUtils.copyInputStreamToFile(imageFile.inputStream, File(filePath))

        return UploadImageResponse(fileName)
    }

    private fun isPng(imageFile: MultipartFile) =
        FilenameUtils.getExtension(imageFile.originalFilename) == "png"

    private fun buildFilePath(fileName: String): String = "src/main/resources/drawings/$fileName"

    private fun buildFileName(): String = "${UUID.randomUUID().toString().replace("-", "").toUpperCase()}.png"
}
