package com.coder_rangers.mobius_api.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.regions.Regions.SA_EAST_1
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonS3ClientConfig @Autowired constructor(
    @Value("\${s3.access_key_id}")
    private val accessKeyId: String,

    @Value("\${s3.secret_key}")
    private val secretKey: String
) {
    @Bean
    fun amazonS3Client(): AmazonS3 = AmazonS3ClientBuilder.standard()
        .withRegion(SA_EAST_1)
        .withCredentials(
            AWSStaticCredentialsProvider(
                BasicAWSCredentials(accessKeyId, secretKey)
            )
        )
        .build()
}
