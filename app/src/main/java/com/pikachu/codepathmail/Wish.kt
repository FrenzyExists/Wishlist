package com.pikachu.codepathmail

import java.text.DateFormat
import java.util.Currency
import java.util.Locale

class Wish(
    val item: String,
    val store: String,
    val price: Float,
    val currency: String
) {
    init {
        val locale = currencyLocales[currency]
        val curr = if (locale != null) {
            Currency.getInstance(currency)
        } else {
            Currency.getInstance("XDR")
        }

        val symbol = curr.getSymbol(locale)
    }
}

public val currencyLocales = mapOf(
    "AUD" to Locale("en", "AU"),
    "AWG" to Locale("nl", "AW"),
    "AZN" to Locale("az", "AZ"),
    "BZD" to Locale("en", "BZ"),
    "CAD" to Locale.CANADA,
    "CDF" to Locale("fr", "CD"),
    "CHF" to Locale("de", "CH"),
    "CLP" to Locale("es", "CL"),
    "CNY" to Locale.CHINA,
    "CZK" to Locale("cs", "CZ"),
    "DJF" to Locale("fr", "DJ"),
    "DKK" to Locale("da", "DK"),
    "DOP" to Locale("es", "DO"),
    "DZD" to Locale("ar", "DZ"),
    "EGP" to Locale("ar", "EG"),
    "ERN" to Locale("ti", "ER"),
    "ETB" to Locale("am", "ET"),
    "EUR" to Locale("en", "EU"),
    "GBP" to Locale.UK,
    "XDR" to Locale("mul", "INTERNATIONAL"),
    "USD" to Locale.US,
    "JPY" to Locale.JAPAN
)