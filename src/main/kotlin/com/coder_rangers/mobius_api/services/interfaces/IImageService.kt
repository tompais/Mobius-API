package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.responses.SaveImageResponse

interface IImageService {
    fun uploadImageToS3(bytes: ByteArray): SaveImageResponse
    fun getImageFromS3(fileName: String): ByteArray
    fun compareImages(originalImageInBase64: String, drawnImageInBase64: String): Double
}
