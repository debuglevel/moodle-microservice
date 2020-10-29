package de.debuglevel.moodlebridge.site

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/sites")
@Tag(name = "sites")
class SiteController(private val siteService: SiteService) {
    private val logger = KotlinLogging.logger {}

    @Get("/")
    fun getAll(): HttpResponse<*> {
        logger.debug("Called getAll()")

        return try {
            val site = siteService.get()
            HttpResponse.ok(site)
//            val getPersonResponses = persons
//                .map { GetPersonResponse(it) }
//
//            HttpResponse.ok(getPersonResponses)
        } catch (e: Exception) {
            logger.error(e) { "Unhandled exception" }
            HttpResponse.serverError("Unhandled exception: ${e.message}")
        }
    }
}