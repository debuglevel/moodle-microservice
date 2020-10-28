package de.debuglevel.moodlebridge.greeting

data class GreetingRequest(
    val name: String,
    val language: String?
)
