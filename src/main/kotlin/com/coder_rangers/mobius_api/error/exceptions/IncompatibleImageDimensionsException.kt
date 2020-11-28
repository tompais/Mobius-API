package com.coder_rangers.mobius_api.error.exceptions

class IncompatibleImageDimensionsException(
    originalImageWidth: Int,
    originalImageHeight: Int,
    drawnImageWidth: Int,
    drawnImageHeight: Int
) : BadRequestException("Images must have the same dimensions: ($originalImageWidth,$originalImageHeight) vs. ($drawnImageWidth,$drawnImageHeight).")
