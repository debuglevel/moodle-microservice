package de.debuglevel.moodlebridge.site

open class MoodleCall(
    var wsfunction: String,
    var wstoken: String = "",
    var moodlewsrestformat: String = "",
)