package de.debuglevel.moodlebridge.site

import de.debuglevel.moodlebridge.site.client.GetSiteResponse
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class SiteService(@param:Client("\${app.moodlebridge.url}") private val httpClient: RxHttpClient) {
    private val logger = KotlinLogging.logger {}

    fun callRemote(params: MoodleCall, clazz: Class<GetSiteResponse>): String {
        val uri = UriBuilder.of("/webservice/rest/server.php")
            .toString()

        params.moodlewsrestformat = "json"
        params.wstoken = "32bc217501ebb9de79ffffbfa13eda81"

        val result = httpClient
            .toBlocking()
            .retrieve(HttpRequest.POST<Any>(uri, params), String::class.java)
        return result
    }

    fun get(): Site {
        logger.debug { "Getting site..." }

        val params = SiteMoodleCall("core_webservice_get_site_info")
        val result = callRemote(params, GetSiteResponse::class.java)


        logger.debug { "Got site: $site" }
        return person
    }


    class EntityNotFoundException(criteria: Any) : Exception("Entity '$criteria' does not exist.")
}