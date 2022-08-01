package com.example.githubusersearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitHubUserRepositoryListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_user_repository_list)

        Log.d("mytag", intent.getStringExtra("u_id")!!)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(GitHubAPIService::class.java)
        val id = intent.getStringExtra("u_id").toString()
        val apiCallForData = apiService.getRepos(id, "token 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")
        apiCallForData.enqueue(object : Callback<List<GitHubRepos>> {
            override fun onResponse(
                call: Call<List<GitHubRepos>>,
                response: Response<List<GitHubRepos>>
            ) {
                val data = response.body()!!
                Log.d("mytag", data.toString())

                val listView = findViewById<RecyclerView>(R.id.githubrepos_list)
                listView.setHasFixedSize(true)
                //3
                listView.layoutManager = LinearLayoutManager(this@GitHubUserRepositoryListActivity)
                //4,5
                listView.adapter = GitHubReposAdapter(data)

                listView.setHasFixedSize(true)

            }

            override fun onFailure(call: Call<List<GitHubRepos>>, t: Throwable) {
            }

        })
        }

    }