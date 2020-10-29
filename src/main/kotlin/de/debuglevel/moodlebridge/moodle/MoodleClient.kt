package de.debuglevel.moodlebridge.moodle

import io.micronaut.context.annotation.Property
import io.micronaut.http.HttpRequest
import io.micronaut.http.MediaType
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.uri.UriBuilder
import mu.KotlinLogging
import javax.inject.Singleton

@Singleton
class MoodleClient(
    @param:Client("\${app.moodlebridge.url}") private val httpClient: RxHttpClient,
    @Property(name = "app.moodlebridge.token") private val token: String,
) {
    private val logger = KotlinLogging.logger {}

    fun callRemote(body: MoodleCall, clazz: Class<*>): Any {
        logger.debug { "Calling Moodle webservice function '${body.wsfunction}': $body" }

        val uri = UriBuilder.of("/webservice/rest/server.php")
            .toString()

        logger.trace { "Configuring webservice call..." }
        body.moodlewsrestformat = "json"
        body.wstoken = token

        val result = httpClient
            .toBlocking()
            .retrieve(
                HttpRequest.POST<Any>(uri, body)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED),
                clazz
            )

        logger.debug { "Called Moodle webservice function '${body.wsfunction}': $result" }
        return result
    }
}