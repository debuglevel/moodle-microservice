package de.debuglevel.moodlebridge.moodle

open class MoodleCall(
    var wsfunction: String,
    var wstoken: String = "",
    var moodlewsrestformat: String = "",
)