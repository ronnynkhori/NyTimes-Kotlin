package com.example.nytimes

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article.view.*

class MainAdapter(val articles: Articles): RecyclerView.Adapter<CustomViewHolder>() {

    override fun getItemCount(): Int {
        return  articles.results.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.article, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val article = articles.results.get(position)
        holder.view.bylineTxt?.text = article.byline
        holder.view.titleTxt?.text = article.title
        holder.view.dateTxt?.text = article.published_date


        holder.article = article
    }
}

class CustomViewHolder(val view: View, var article:Article? = null): RecyclerView.ViewHolder(view){

init{
    view.setOnClickListener {
        val intent = Intent(view.context, ArticleDetails::class.java)
        intent.putExtra("title", article?.title)
        intent.putExtra("byline", article?.byline)
        intent.putExtra("date", article?.published_date)
        intent.putExtra("abstract", article?.abstract)
        if(article?.media?.isNotEmpty() == true){
            intent.putExtra("image", article?.media!![0].mediametadata[2]?.url)
        }

        view.context.startActivity(intent)
    }
}
}