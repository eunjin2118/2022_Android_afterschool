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

}

data class GitHubUser(
    val login: String,
    val id: Int,
    val name: String,
    val followers: Int,
    val following: Int,
    val avatar_url: String
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