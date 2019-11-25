package com.d9tilov.currencyapp.utils

import java.util.*

object CurrencyUtils {
    private const val ASCII_OFFSET = 0x41
    private const val UNICODE_FLAG_OFFSET = 0x1F1E6
    private const val CURRENCY_PLACEHOLDER_MASK = "0%s00"
    private const val CURRENCY_OUTPUT_MASK = "%.2f"

    fun getCurrencyFullName(code: String): String = Currency.getInstance(code).displayName

    fun getCurrencySignBy(code: String): String = Currency.getInstance(code).symbol

    fun getCurrencyIcon(code: String): String {
        val firstChar = Character.codePointAt(code, 0) - ASCII_OFFSET + UNICODE_FLAG_OFFSET
        val secondChar = Character.codePointAt(code, 1) - ASCII_OFFSET + UNICODE_FLAG_OFFSET
        return String(Character.toChars(firstChar)) + String(Character.toChars(secondChar))
    }
}
