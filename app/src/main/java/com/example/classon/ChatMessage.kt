package com.example.classon

data class ChatMessage(
    val text: String,
    val isUser: Boolean = false,
    val isTyping: Boolean = false
)
