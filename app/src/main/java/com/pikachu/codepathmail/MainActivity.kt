package com.pikachu.codepathmail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

lateinit var emails: MutableList<Email>

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailsRv = findViewById<RecyclerView>(R.id.emailReciclerView)
        val loadMore = findViewById<Button>(R.id.load_button)

        emails = EmailFetcher.getEmails()
        val adapter = EmailAdapter(emails)
        emailsRv.adapter = adapter
        emailsRv.layoutManager = LinearLayoutManager(this)

        loadMore.setOnClickListener {
            emails += EmailFetcher.getNext5Emails()
            EmailAdapter(emails)
            emailsRv.adapter = adapter
            emailsRv.layoutManager = LinearLayoutManager(this)
        }
    }
}

