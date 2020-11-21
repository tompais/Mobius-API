package com.coder_rangers.mobius_api.services.interfaces

import java.io.InputStream

interface IAmazonS3Service {
    fun uploadFileToS3(filePath: String, inputStream: InputStream)
}
