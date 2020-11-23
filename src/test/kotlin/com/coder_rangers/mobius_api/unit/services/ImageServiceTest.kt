package com.coder_rangers.mobius_api.unit.services

import assertk.assertThat
import assertk.assertions.isFailure
import assertk.assertions.isInstanceOf
import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.services.implementations.ImageService
import com.coder_rangers.mobius_api.services.interfaces.IAmazonS3Service
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockkStatic
import org.apache.commons.io.FilenameUtils
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mock.web.MockMultipartFile

@ExtendWith(MockKExtension::class)
class ImageServiceTest {
    @RelaxedMockK
    @Suppress("UNUSED")
    private lateinit var amazonS3Service: IAmazonS3Service

    @InjectMockKs
    private lateinit var imageService: ImageService

    @Test
    fun fileExtensionNullTest() {
        // GIVEN
        mockkStatic(FilenameUtils::class).also {
            every { FilenameUtils.getExtension(any()) } returns null
        }

        // THEN
        assertThat {
            imageService.saveImage(
                MockMultipartFile(
                    "image-file",
                    null,
                    null,
                    "test data".toByteArray()
                )
            )
        }.isFailure().isInstanceOf(IllegalImageExtensionException::class)
    }
}
