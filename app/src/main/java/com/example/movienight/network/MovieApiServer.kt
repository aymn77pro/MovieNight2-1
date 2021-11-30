package com.example.movienight.network

import android.os.Build.VERSION_CODES.M
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL =
    "https://api.themoviedb.org/"
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()
interface MovieApiServer{
    @GET("3/movie/popular?api_key=8c89f63f1970a7c5a4ce4c3e270a0cee&language=en-US&")
    suspend fun getPhoto(@Query("page")page:Int,@Query("with_genres")genres:Int):Response
}
object MovieApi{
    val retrofitServer : MovieApiServer by lazy {
        retrofit.create(MovieApiServer::class.java)
    }
}
