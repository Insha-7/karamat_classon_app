package com.example.classon

object GeminiRepository {
    private val api = GeminiApiClient.retrofit.create(GeminiApi::class.java)
    private const val MODEL = "gemini-2.5-flash"

    suspend fun askGemini(prompt: String, maxChars: Int = 20000): String {
        val safePrompt = if (prompt.length > maxChars) {
            prompt.take(maxChars) + "\n\n[...context truncated]"
        } else prompt

        val request = GeminiRequest(
            contents = listOf(Content(parts = listOf(Part(text = safePrompt))))
        )

        val response = api.generateContent(MODEL, request)
        val candidate = response.candidates?.firstOrNull()
        val text = candidate?.content?.parts?.firstOrNull()?.text
        return text ?: "Sorry — no response from Gemini."
    }
}
