package de.debuglevel.moodlebridge.site.client

data class CoreWebserviceGetSiteInfoResponse(
    val sitename: String,
    val siteurl: String,
    val release: String,
    val version: String,
)