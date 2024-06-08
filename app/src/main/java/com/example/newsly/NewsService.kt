package com.example.newsly

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//666c31c09f5c4229974ab1ef9b889993

//https://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//https://newsapi.org/v2/everything?q=apple&from=2024-02-08&to=2024-02-08&sortBy=popularity&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "666c31c09f5c4229974ab1ef9b889993"

interface NewsInterface{

    @GET("/v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country") country: String,@Query("page") page: Int) : Call<News>

    //https://newsapi.org//v2/top-headlines?apiKey=$API_KEY&country=in&page=1

}


object NewsService{
    val newsInstance: NewsInterface
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
    //first change

}
