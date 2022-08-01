package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface GitHubAPIService {
    @GET("/users/{id}")
    fun getGitHubId(
        @Path("id") id: String,
        @Header("Authorization") pat:String
    ) : Call<GitHubUser>

    @GET("/users/{id}/repos")
    fun getRepos(
        @Path("id") id: String,
        @Header("Authorization") pat:String
    ) : Call<List<GitHubRepos>>

}

data class GitHubUser(
    val login: String,
    val id: Int,
    val name: String,
    val followers: Int,
    val following: Int,
    val avatar_url: String
)

data class GitHubRepos(
    val name: String,
    val html_url: String,
    val description: String?,
    val forks_count: Int,
    val watchers_count: Int,
    val stargazers_count: Int
)

//
//class GitHubResponseDeseralizerGSON : JsonDeserializer<GitHubUser>{
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): GitHubUser {
//        val root = json?.asJsonObject
//        val login = root?.getAsJsonPrimitive("login")?.getAsString()
//        val id = root?.getAsJsonPrimitive("id")?.getAsInt()
//
//        return GitHubUser(login!!, id!!)
//    }
//
//}


//
//class GitHubResponseDeseralizer : JsonDeserializer<GitHubOption>{
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): GitHubOption {
//        val root = json?.asJsonObject
//        val login = root?.getAsJsonPrimitive("login")?.getAsString()
//        val id = root?.getAsJsonPrimitive("id")?.getAsInt()
//
//        return GitHubOption(login!!, id!!)
//    }
//
//}
