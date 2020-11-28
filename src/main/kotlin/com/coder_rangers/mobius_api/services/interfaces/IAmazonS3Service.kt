package com.coder_rangers.mobius_api.services.interfaces

interface IAmazonS3Service {
    fun uploadFileToS3(filePath: String, byteArray: ByteArray)
}
