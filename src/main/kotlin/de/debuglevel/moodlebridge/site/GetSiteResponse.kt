package de.debuglevel.moodlebridge.site

import de.debuglevel.moodlebridge.site.client.CoreWebserviceGetSiteInfoResponse

data class GetSiteResponse(
    val siteName: String,
    val siteUrl: String,
    val release: String,
    val version: String,
) {
    constructor(coreWebserviceGetSiteInfoResponse: CoreWebserviceGetSiteInfoResponse) : this(
        siteName = coreWebserviceGetSiteInfoResponse.sitename,
        siteUrl = coreWebserviceGetSiteInfoResponse.siteurl,
        release = coreWebserviceGetSiteInfoResponse.release,
        version = coreWebserviceGetSiteInfoResponse.version,
    )
}