package com.example.newsly

import android.net.DnsResolver.Callback
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsly.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NewsAdapter
    lateinit var newsList:RecyclerView
//    private lateinit var binding:ActivityMainBinding
    private lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        text=findViewById(R.id.text)
        newsList=findViewById(R.id.NewsList)
//        adapter = NewsAdapter(this@MainActivity,news.articles)

        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
        getNews()
    }

    private fun getNews(){
        val news = NewsService.newsInstance.getHeadlines("in",1)
        news.enqueue(object: retrofit2.Callback<News>{
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news!=null){
                    Log.d("YojitKapoor", news.toString())

                    if(news.articles != null) {
                        adapter = NewsAdapter(this@MainActivity, news.articles)
                        newsList.adapter = adapter
                        newsList.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("YojitKapoor","Error in Fetching News")
            }
        })
    }


}