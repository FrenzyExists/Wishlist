package com.pikachu.codepathmail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class WishAdapter( private var wishes: MutableList<Wish>): RecyclerView.Adapter<WishAdapter.ViewHolder>(){
    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onEditClick(position: Int)
        fun onDeleteClick(position: Int)
        fun onItemClicked(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val optionsTextView: ImageButton
        val titleTextView: TextView
        val summaryTextView: TextView
        val dateTextView: TextView

        init {
            optionsTextView = itemView.findViewById(R.id.optionsTv)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
            dateTextView = itemView.findViewById(R.id.dateTv)

            // Set OnClickListener for the entire itemView
            itemView.setOnClickListener(this)

            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onEditClick(position)
                }
                true
            }
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener?.onItemClicked(position)
            }
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
        val wishView = inflater.inflate(R.layout.wish_item, parent, false)
        // Return a new holder instance
        return ViewHolder(wishView)
    }

    override fun getItemCount(): Int {
        // Number of Items
        return wishes.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // to set the view attributes based on the data
        // Involves populating data into the item through holder

        // Get the data model based on position
        val wish: Wish = wishes[position]
        // Set item views based on your views and data model
        holder.titleTextView.text = wish.item
        holder.summaryTextView.text = wish.store
        holder.dateTextView.text = "${wish.price} ${wish.currency}"

        // Set OnClickListener for options button
        holder.optionsTextView.setOnClickListener {
            val popupMenu = PopupMenu(holder.optionsTextView.context, holder.optionsTextView)
            popupMenu.menuInflater.inflate(R.menu.options, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_edit -> {
                        listener?.onEditClick(position)
                        // Handle Edit action
                        true
                    }
                    R.id.menu_delete -> {
                        // Handle Delete action
                        listener?.onDeleteClick(position)
                        true
                    }
                    else -> false
                }
            }

            popupMenu.show()
        }


    }
}