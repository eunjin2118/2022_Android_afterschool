package com.example.githubusersearch

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.lang.reflect.Type

interface GitHubAPIService {
    @GET("/user/{id}")
    fun getGitHubIdInfo(
        @Path("id") id: Int
    ) : Call<GitHubIdJSONResponseGSON>

}

data class GitHubIdJSONResponseGSON(
    val login: String,
    val id: Int
)

class GitHubResponseDeseralizerGSON : JsonDeserializer<GitHubIdJSONResponseGSON>{
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): GitHubIdJSONResponseGSON {
        val root = json?.asJsonObject
        val login = root?.getAsJsonPrimitive("login")?.getAsString()
        val id = root?.getAsJsonPrimitive("id")?.getAsInt()

        return GitHubIdJSONResponseGSON(login!!, id!!)
    }

}