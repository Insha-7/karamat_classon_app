package com.example.classon

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface GeminiApi {
    // model path in the URL - we will pass model name as path param
    @POST("models/{model}:generateContent")
    suspend fun generateContent(
        @Path("model") model: String,
        @Body request: GeminiRequest
    ): GeminiResponse
}
