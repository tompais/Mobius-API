package com.coder_rangers.mobius_api.utils

import org.xhtmlrenderer.pdf.ITextRenderer
import java.io.ByteArrayOutputStream

fun String.toPDF(): ByteArray = ByteArrayOutputStream().use { byteArrayOutputStream ->
    ITextRenderer().let { textRenderer ->
        textRenderer.setDocumentFromString(this)
        textRenderer.layout()
        textRenderer.createPDF(byteArrayOutputStream)
    }
    byteArrayOutputStream.toByteArray()
}
