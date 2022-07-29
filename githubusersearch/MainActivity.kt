package com.example.githubusersearch

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import androidx.viewpager.widget.ViewPager
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = findViewById<EditText>(R.id.input_id)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        GitHubIdJSONResponseGSON::class.java,
                        GitHubResponseDeseralizerGSON()
                    ).create()
                )
            )
            .build()

        val apiService = retrofit.create(GitHubAPIService::class.java)
        val apiCallForData = apiService.getDustStatusInfo(login!!, id!!)

    }
}