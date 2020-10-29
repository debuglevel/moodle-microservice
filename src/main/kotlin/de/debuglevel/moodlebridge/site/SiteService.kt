package de.debuglevel.moodlebridge.site

import de.debuglevel.moodlebridge.moodle.MoodleClient
import de.debuglevel.moodlebridge.site.client.CoreWebserviceGetSiteInfoRequest
import de.debuglevel.moodlebridge.site.client.CoreWebserviceGetSiteInfoResponse
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class SiteService(
    private val moodleClient: MoodleClient,
) {
    private val logger = KotlinLogging.logger {}

    fun get(): CoreWebserviceGetSiteInfoResponse {
        logger.debug { "Getting site..." }

        val moodleCall = CoreWebserviceGetSiteInfoRequest()
        val site = moodleClient.callRemote(
            moodleCall,
            CoreWebserviceGetSiteInfoResponse::class.java
        ) as CoreWebserviceGetSiteInfoResponse

        logger.debug { "Got site: $site" }
        return site
    }

    class EntityNotFoundException(criteria: Any) : Exception("Entity '$criteria' does not exist.")
}