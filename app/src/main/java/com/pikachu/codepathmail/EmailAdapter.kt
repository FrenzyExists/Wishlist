package com.pikachu.codepathmail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Locale

class EmailAdapter(private val emails: List<Email>): RecyclerView.Adapter<EmailAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // val sender: String,
        // val title: String,
        // val summary: String,
        // val date: DateFormat
        val senderTextView: TextView
        val titleTextView: TextView
        val summaryTextView: TextView
        val dateTextView: TextView

        init {
            senderTextView = itemView.findViewById(R.id.senderTv)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
            dateTextView = itemView.findViewById(R.id.dateTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /**
         *  inflate the item layout and create the holder
         *  Usually involves inflating a layout from XML and returning the holder
         */
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val emailView = inflater.inflate(R.layout.email_item, parent, false)
        // Return a new holder instance
        return ViewHolder(emailView)
    }

    override fun getItemCount(): Int {
        // Number of Items
        return emails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // to set the view attributes based on the data
        // Involves populating data into the item through holder

        // Get the data model based on position
        val email: Email = emails[position]
        // Set item views based on your views and data model
        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.summaryTextView.text = email.summary
        val calendar = email.date.calendar

        val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(calendar.time)
        holder.dateTextView.text = formattedDate

    }
}