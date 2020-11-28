package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.error.exceptions.IncompatibleImageDimensionsException
import java.awt.image.BufferedImage
import java.net.URLConnection
import kotlin.math.abs

object ImageUtils {
    fun getImageDifferenceInPercent(originalImage: BufferedImage, drawnImage: BufferedImage): Double {
        val originalImageWidth = originalImage.width
        val originalImageHeight = originalImage.height
        val drawnImageWidth = drawnImage.width
        val drawnImageHeight = drawnImage.height

        if (originalImageWidth != drawnImageWidth || originalImageHeight != drawnImageHeight) {
            throw IncompatibleImageDimensionsException(
                originalImageWidth,
                originalImageHeight,
                drawnImageWidth,
                drawnImageHeight
            )
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

    private fun isPng(bytes: ByteArray): Boolean {
        val extension = URLConnection.guessContentTypeFromStream(bytes.inputStream()).split("/")[1]

        return "png".equals(extension, ignoreCase = true)
    }

    fun assertThatIsAPNG(bytes: ByteArray) {
        if (!isPng(bytes)) {
            throw IllegalImageExtensionException()
        }
    }
}
