package de.debuglevel.moodlebridge.person

import java.util.*

data class GetPersonResponse(
    val id: UUID,
    val name: String,
) {
    constructor(person: Person) : this(
        person.id!!,
        person.name,
    )
}