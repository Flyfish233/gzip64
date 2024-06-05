package com.flyfish233.gzip64.kotlin

import okio.*
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

object Gzip64 {
    fun encode(byteArray: ByteArray): String {
        val buffer = Buffer().write(byteArray)

        GzipSink(buffer).buffer().apply {
            write(buffer, buffer.size)
            close()
        }

        return buffer.readByteString().base64().also {
            buffer.close()
        }
    }

    fun encode(str: String): String = encode(str.toByteArray())

    @OptIn(ExperimentalEncodingApi::class)
    fun decodeToSrc(base64String: String): Source =
        GzipSource(Buffer().write(Base64.decode(base64String)))

    fun decodeToString(base64String: String): String =
        decodeToSrc(base64String).buffer().readUtf8()
}