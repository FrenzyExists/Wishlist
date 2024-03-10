package com.pikachu.codepathmail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.webkit.URLUtil
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity(), WishAdapter.OnItemClickListener {

    private lateinit var adapter: WishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishesRv = findViewById<RecyclerView>(R.id.wishReciclerView)
        val loadMore = findViewById<Button>(R.id.buttonAdd)
        val spinner = findViewById<Spinner>(R.id.currencySpinner)

        val priceText = findViewById<EditText>(R.id.editTextCurrency)
        val storeOrURLText = findViewById<EditText>(R.id.editTextStoreOrURL)
        val wishText = findViewById<TextView>(R.id.editTextWish)

        val currencyCodes = currencyLocales.keys.toList()
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencyCodes)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter

        var selectedCurrencyCodeBoi: String = ""
        var selectedLocale: Locale?

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCurrencyCodeBoi = parent?.getItemAtPosition(position).toString()
                selectedLocale = currencyLocales[selectedCurrencyCodeBoi]
                println("Selected currency code: $selectedCurrencyCodeBoi")
                println("Selected locale: $selectedLocale")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        loadMore.setOnClickListener {
            if(priceText.text.isNotEmpty() && storeOrURLText.text.isNotEmpty() && wishText.text.isNotEmpty()) {
                WishFetcher.addItem(
                    wishText.text.toString(),
                    storeOrURLText.text.toString(),
                    priceText.text.toString().toFloat(),
                    selectedCurrencyCodeBoi
                )
                val position = WishFetcher.getWishes().size - 1
                adapter.notifyItemInserted(position)
                wishesRv.scrollToPosition(position)
            } else {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show()
            }
        }

        adapter = WishAdapter(WishFetcher.getWishes())
        adapter.setOnItemClickListener(this)
        wishesRv.adapter = adapter
        wishesRv.layoutManager = LinearLayoutManager(this)
    }

    override fun onEditClick(position: Int) {
        Log.v("Pressing", "EDIT $position")
        // Handle Edit action here
    }

    override fun onDeleteClick(position: Int) {
        Log.v("Pressing", "DELETE $position")
        if (position != RecyclerView.NO_POSITION) {
            WishFetcher.deleteWish(position)
            adapter.notifyItemRemoved(position)
        }
    }

    override fun onItemClicked(position: Int) {
        Log.v("Pressing", "THE WHOLE ITEM $position")
        val wish = WishFetcher.getWish(position)
        if (wish.store.isNotEmpty() && isValidUrl(wish.store)) {
            openWebPage(wish.store)
        }
    }

    private fun openWebPage(url: String) {
        val urlWithHttp = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
        val webpage = Uri.parse(urlWithHttp)
        Log.e("OpenWebPage", "URL: $urlWithHttp")

        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.e("OpenWebPage", "No activity found to handle Intent for URL: $urlWithHttp")
            Toast.makeText(this, "Could not open URL", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidUrl(url: String): Boolean {
        val urlWithHttp = if (!url.startsWith("http://") && !url.startsWith("https://")) {
            "https://$url"
        } else {
            url
        }
        return try {
            URLUtil.isValidUrl(urlWithHttp) && Patterns.WEB_URL.matcher(urlWithHttp).matches()
        } catch (e: Exception) {
            false
        }
    }
}
