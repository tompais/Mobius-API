package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.error.exceptions.IncompatibleImageDimensionsException
import java.awt.image.BufferedImage
import kotlin.math.abs

object ImageUtils {
    fun getImageDifferenceInPercent(originalImage: BufferedImage, drawnImage: BufferedImage): Double {
        val originalImageWidth = originalImage.width
        val originalImageHeight = originalImage.height
        val drawnImageWidth = drawnImage.width
        val drawnImageHeight = drawnImage.height

        if (originalImageWidth != drawnImageWidth || originalImageHeight != drawnImageHeight) {
            val dimensionsDifference =
                "(%d,%d) vs. (%d,%d)".format(originalImageWidth, originalImageHeight, drawnImageWidth, drawnImageHeight)
            throw IncompatibleImageDimensionsException(dimensionsDifference)
        }
        var difference = 0L
        for (y in 0 until originalImageHeight) {
            for (x in 0 until originalImageWidth) {
                difference += getPixelDifference(originalImage.getRGB(x, y), drawnImage.getRGB(x, y))
            }
        }
        val maxDifference = 3L * 255 * originalImageWidth * originalImageHeight
        return 100.0 * (1.0 - difference.toDouble() / maxDifference)
    }

    private fun getPixelDifference(originalImageRGB: Int, imageDrawnRGB: Int): Int {
        val originalImageRed = (originalImageRGB shr 16) and 0xff
        val originalImageGreen = (originalImageRGB shr 8) and 0xff
        val originalImageBlue = originalImageRGB and 0xff
        val drawnImageRed = (imageDrawnRGB shr 16) and 0xff
        val drawnImageGreen = (imageDrawnRGB shr 8) and 0xff
        val drawnImageBlue = imageDrawnRGB and 0xff
        return abs(originalImageRed - drawnImageRed) + abs(originalImageGreen - drawnImageGreen) + abs(originalImageBlue - drawnImageBlue)
    }
}
