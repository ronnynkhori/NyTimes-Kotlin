package com.example.nytimes

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.article_details.*
import kotlinx.android.synthetic.main.article_details.view.*

class ArticleDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.article_details)
        val title = intent.getStringExtra("title")
        val byline = intent.getStringExtra("byline")
        val date = intent.getStringExtra("date")
        val abstract = intent.getStringExtra("abstract")
        val image = intent.getStringExtra("image")

        detailTitleText?.text = title
        detailBylineTxt.text = byline
        detailDateTxt.text = date
        detailAbstractTxt.text = abstract
        val thumbnail = detailImageView
        Picasso.get().load(image).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(thumbnail)
    }
}