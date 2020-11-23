package com.coder_rangers.mobius_api.integrations

import com.amazonaws.services.s3.AmazonS3
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.util.ResourceUtils

class ImageIntegrationTest : BaseIntegrationTest("/images") {
    @MockkBean
    private lateinit var amazonS3Client: AmazonS3

    private companion object {
        @JvmStatic
        @Suppress("UNUSED")
        fun uploadImageCases() = listOf(
            Arguments.of(
                "spiderman.jpg",
                BAD_REQUEST
            ),
            Arguments.of(
                "spiderman.png",
                OK
            )
        )
    }

    @ParameterizedTest
    @MethodSource("uploadImageCases")
    fun uploadImageTest(fileNameToUpload: String, expectedHttpStatus: HttpStatus) {
        every { amazonS3Client.putObject(any(), any(), any(), any()) } returns mockk()

        given()
            .accept(JSON)
            .multiPart("imageFile", ResourceUtils.getFile("classpath:images/$fileNameToUpload"))
            .`when`()
            .post(baseUrl)
            .then()
            .status(expectedHttpStatus)
    }
}
