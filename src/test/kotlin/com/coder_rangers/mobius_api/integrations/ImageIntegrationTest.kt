package com.coder_rangers.mobius_api.integrations

import io.mockk.justRun
import io.mockk.mockkStatic
import io.restassured.http.ContentType.JSON
import io.restassured.module.mockmvc.RestAssuredMockMvc.given
import org.apache.commons.io.FileUtils
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.OK
import org.springframework.util.ResourceUtils

class ImageIntegrationTest : BaseIntegrationTest("/image") {
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
        mockkStatic(FileUtils::class).also {
            justRun { FileUtils.copyInputStreamToFile(any(), any()) }
        }

        given()
            .accept(JSON)
            .multiPart("imageFile", ResourceUtils.getFile("classpath:images/$fileNameToUpload"))
            .`when`()
            .post("$baseUrl/upload")
            .then()
            .status(expectedHttpStatus)
    }
}
