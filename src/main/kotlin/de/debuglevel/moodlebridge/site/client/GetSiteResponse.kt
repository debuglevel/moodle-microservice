package de.debuglevel.moodlebridge.site.client

data class GetSiteResponse(
    val sitename: String,
    val siteurl: String,
    val release: String,
    val version: String,
)