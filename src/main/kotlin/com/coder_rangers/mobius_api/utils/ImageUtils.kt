package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import com.coder_rangers.mobius_api.error.exceptions.IncompatibleImageDimensionsException
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.runBlocking
import java.awt.image.BufferedImage
import java.net.URLConnection
import java.util.concurrent.Executors.newFixedThreadPool
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

        val threadPool = newFixedThreadPool(3).asCoroutineDispatcher()
        val difference = runBlocking(threadPool) {
            (0 until originalImageHeight).flatMap { y ->
                (0 until originalImageWidth).map { x ->
                    run {
                        getPixelDifference(originalImage.getRGB(x, y), drawnImage.getRGB(x, y))
                    }
                }
            }
        }.sum()

        val maxDifference = 3L * 255 * originalImageWidth * originalImageHeight
        return 100.0 * (1.0 - difference.toDouble() / maxDifference)
    }

    private fun getPixelDifference(originalImageRGB: Int, drawnImageRGB: Int): Int {
        val originalImageRed = (originalImageRGB shr 16) and 0xff
        val originalImageGreen = (originalImageRGB shr 8) and 0xff
        val originalImageBlue = originalImageRGB and 0xff
        val drawnImageRed = (drawnImageRGB shr 16) and 0xff
        val drawnImageGreen = (drawnImageRGB shr 8) and 0xff
        val drawnImageBlue = drawnImageRGB and 0xff
        return abs(originalImageRed - drawnImageRed) + abs(originalImageGreen - drawnImageGreen) + abs(originalImageBlue - drawnImageBlue)
    }

    private fun isPng(bytes: ByteArray): Boolean {
        val extension = URLConnection.guessContentTypeFromStream(bytes.inputStream()).split("/")[1]

        return "png".equals(extension, ignoreCase = true)
    }

    private fun isPng(fileName: String): Boolean = fileName.endsWith(".png")

    fun assertThatIsAPNG(bytes: ByteArray) {
        if (!isPng(bytes)) {
            throw IllegalImageExtensionException()
        }
    }

    fun assertThatIsAPNG(fileName: String) {
        if (!isPng(fileName)) {
            throw IllegalImageExtensionException()
        }
    }
}
