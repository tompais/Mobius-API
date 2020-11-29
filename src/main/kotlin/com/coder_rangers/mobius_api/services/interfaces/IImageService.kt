package com.coder_rangers.mobius_api.services.interfaces

import com.coder_rangers.mobius_api.responses.SaveImageResponse

interface IImageService {
    fun saveImage(bytes: ByteArray): SaveImageResponse
}
