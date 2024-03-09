package com.pikachu.codepathmail

import java.text.DateFormat

class Email(
    val sender: String,
    val title: String,
    val summary: String,
    val date: DateFormat
) {
}