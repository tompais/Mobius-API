package com.coder_rangers.mobius_api.controllers

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.responses.UploadImageResponse
import com.coder_rangers.mobius_api.services.interfaces.IImageService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.OK
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Validated
@RequestMapping("/images", consumes = [MULTIPART_FORM_DATA_VALUE], produces = [APPLICATION_JSON_VALUE])
class ImageController @Autowired constructor(
    private val imageService: IImageService
) {
    @PostMapping
    @ResponseStatus(OK)
    @Operation(summary = "Endpoint to upload an image")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The image was uploaded successfully.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = UploadImageResponse::class
                            )
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "The image extension was not a PNG.",
                content = [
                    Content(
                        mediaType = APPLICATION_JSON_VALUE,
                        array = ArraySchema(
                            schema = Schema(
                                implementation = IllegalImageExtensionException::class
                            )
                        )
                    )
                ]
            )
        ]
    )
    fun uploadImage(@RequestParam imageFile: MultipartFile): UploadImageResponse = imageService.saveImage(imageFile)
}
