package com.example.androidapp

import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.io.FileInputStream
import java.net.HttpURLConnection
import java.net.URL
data class JokeCloud (
    @SerializedName("type")
    private val type:String,
    @SerializedName("setup")
    private val maincontent:String,
    @SerializedName("punchline")
    private val punchline:String,
    @SerializedName("id")
    private val id:Int
    ) {
    fun toJoke(): Joke {
        return Joke( maincontent,  punchline)
    }
}


