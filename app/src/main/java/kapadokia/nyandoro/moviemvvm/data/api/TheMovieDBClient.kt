package kapadokia.nyandoro.moviemvvm.data.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

// defining constants
 const val API_KEY="db7f024b088bcaa6db06ba9bf9e4e544"
 const val BASE_URL="https://api.themoviedb.org/3/"
 const val POSTER_BASE_URL="https://image.tmdb.org/t/p/w342"


 object TheMovieDBClient {
     fun getClient(): TheMovieDbInterface{

         // create an interceptor to put our Api key in the URL
         val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("api_key", API_KEY)
                    .build();

             val request = chain.request()
                 .newBuilder()
                 .url(url)
                 .build()

             return@Interceptor chain.proceed(request) // explicitly return value from @ annotation
         }

         // lets add this interceptor in okhttp client
         val okHttpClient = OkHttpClient.Builder()
             .addInterceptor(requestInterceptor)
             .connectTimeout(60, TimeUnit.SECONDS)
             .build()

         return Retrofit.Builder()
             .client(okHttpClient)
             .baseUrl(BASE_URL)
             .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(TheMovieDbInterface::class.java)
     }
 }