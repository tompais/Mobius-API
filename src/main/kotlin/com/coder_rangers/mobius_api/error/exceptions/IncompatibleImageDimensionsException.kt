package com.coder_rangers.mobius_api.error.exceptions

class IncompatibleImageDimensionsException(dimensionsDifference: String) :
    BadRequestException("Images must have the same dimensions: $dimensionsDifference.")
