package com.example.githubusersearch

import android.content.Context
import android.content.Intent
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val useridInput = findViewById<EditText>(R.id.input_id)
        val button = findViewById<Button>(R.id.search)
        val t_login = findViewById<TextView>(R.id.login_text)
        val t_id = findViewById<TextView>(R.id.id_text)
        val t_name = findViewById<TextView>(R.id.name)
        val t_followers = findViewById<TextView>(R.id.followers)
        val t_following = findViewById<TextView>(R.id.following)
        val profile = findViewById<ImageView>(R.id.profile_image);

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(
                GsonConverterFactory.create(
//                    GsonBuilder().registerTypeAdapter(
//                        GitHubUser::class.java,
//                        GitHubResponseDeseralizerGSON()
//                    ).create()
                )
            )
            .build()

        val apiService = retrofit.create(GitHubAPIService::class.java)
        button.setOnClickListener {
            val id = useridInput.text.toString()
            val apiCallForData = apiService.getGitHubId(id, "token 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")
            apiCallForData.enqueue(object : Callback<GitHubUser>{
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    Log.d("mytag", response.code().toString())
                    if((response.code().toString()).startsWith("4")) {
                        Toast.makeText(
                            this@MainActivity,
                            "유저가 없습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        val data = response.body()!!
                        Log.d("mytag", data.toString())
                        t_login.text = "login : ${data.login}"
                        t_id.text = "id : ${data.id.toString()}"
                        t_name.text = "name : ${data.name}"
                        t_followers.text = "followers : ${data.followers.toString()}"
                        t_following.text = "following : ${data.following.toString()}"
                        Glide.with(this@MainActivity).load(data.avatar_url).into(profile)
                    }

                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {

                }
            })
        }

        val userRepoSearch = findViewById<Button>(R.id.usersearch)
        userRepoSearch.setOnClickListener {

            val intent = Intent(this@MainActivity, GitHubUserRepositoryListActivity::class.java)
            intent.putExtra("u_id", useridInput.text.toString())
            startActivity(intent)

        }
    }
}