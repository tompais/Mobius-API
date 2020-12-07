package com.coder_rangers.mobius_api.services.implementations

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.ByteArrayInputStream

@Service
class AmazonS3Service @Autowired constructor(
    private val amazonS3Client: AmazonS3,

    @Value("\${s3.bucket_name}")
    private val bucketName: String
) : IAmazonS3Service {
    override fun uploadFileToS3(filePath: String, byteArray: ByteArray) {
        amazonS3Client.putObject(
            bucketName,
            filePath,
            ByteArrayInputStream(byteArray),
            ObjectMetadata().apply {
                contentLength = byteArray.size.toLong()
            }
        )
    }

    override fun getFileFromS3(filePath: String): ByteArray =
        IOUtils.toByteArray(amazonS3Client.getObject(bucketName, filePath).objectContent)
}
