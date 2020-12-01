package com.coder_rangers.mobius_api.utils

import org.apache.commons.codec.digest.DigestUtils
import java.util.Base64

fun String.fromBase64ToSHA256(): String = Base64.getDecoder().decode(this).let {
    DigestUtils.sha256Hex(it)
}
