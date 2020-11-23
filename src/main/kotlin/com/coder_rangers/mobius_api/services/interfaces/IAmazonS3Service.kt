package com.coder_rangers.mobius_api.services.interfaces

import com.amazonaws.services.s3.model.S3Object

interface IAmazonS3Service {
    fun uploadFileToS3(filePath: String, byteArray: ByteArray)
    fun downloadFileFromS3(filePath: String): S3Object
}
