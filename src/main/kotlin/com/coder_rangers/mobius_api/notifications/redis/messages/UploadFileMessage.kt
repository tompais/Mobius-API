package com.coder_rangers.mobius_api.notifications.redis.messages

data class UploadFileMessage(
    val filePath: String,
    val bytes: ByteArray
) : AbstractMessage() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UploadFileMessage) return false

        if (filePath != other.filePath) return false
        if (!bytes.contentEquals(other.bytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = filePath.hashCode()
        result = 31 * result + bytes.contentHashCode()
        return result
    }
}
