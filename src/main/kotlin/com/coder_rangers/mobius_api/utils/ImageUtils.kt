package com.coder_rangers.mobius_api.utils

import com.coder_rangers.mobius_api.error.exceptions.IllegalImageExtensionException
import java.net.URLConnection

object ImageUtils {
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
