package de.debuglevel.moodlebridge.site.client

import de.debuglevel.moodlebridge.person.AddPersonRequest
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.annotation.Client

@Client("/sites")
interface SiteClient {
    @Post("/")
    fun add(@Body person: AddPersonRequest): GetSiteResponse
}