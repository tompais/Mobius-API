package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.responses.UploadImageResponse
import org.springframework.web.multipart.MultipartFile

interface IImageService {
    fun saveImage(imageFile: MultipartFile): UploadImageResponse
}
