package com.d9tilov.currencyapp.utils

object Utils {

    private const val ASCII_OFFSET = 0x41
    private const val UNICODE_FLAG_OFFSET = 0x1F1E6

    fun getCurrencyFlagEmojiBy(code: String): String {
        val firstChar = Character.codePointAt(code, 0) - ASCII_OFFSET + UNICODE_FLAG_OFFSET
        val secondChar = Character.codePointAt(code, 1) - ASCII_OFFSET + UNICODE_FLAG_OFFSET
        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }
}