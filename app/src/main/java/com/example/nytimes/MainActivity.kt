package com.example.nytimes

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJson()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun fetchJson() {

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Loading data, please wait")
        progressDialog.show()

        val url =
            "https://api.nytimes.com/svc/mostpopular/v2/viewed/7.json?api-key=HaXxCgzwTVwzabiL3TON06R8UAJA7iXa"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                progressDialog.dismiss()
                val gson = GsonBuilder().create()
                val homefeed = gson.fromJson(body, Articles::class.java)
                runOnUiThread {
                    recyclerView_main.adapter = MainAdapter(homefeed)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }

}

class Articles(val results: List<Article>) {

}

class Article(
    val asset_id: String,
    val byline: String,
    val abstract: String,
    val title: String,
    val published_date: String,
    val url: String,
    val media: List<Medias>
)

class Media(val media: List<Medias>) {}

class Medias(
    val type: String,
    val subtype: String,
    val caption: String,
    @SerializedName("media-metadata")
    val mediametadata: List<Url>
)

class Url(
    val url: String
)
