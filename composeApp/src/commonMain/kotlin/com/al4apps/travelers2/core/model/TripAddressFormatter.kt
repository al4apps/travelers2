package com.al4apps.travelers2.core.model

fun TripAddress.toDisplayString(): String =
    locality
        ?.takeIf { it.isNotBlank() }
        ?.let { "$town, $it" }
        ?: town

fun TripAddress.matchesQuery(query: String): Boolean {
    val normalizedQuery = query.trim()
    if (normalizedQuery.isEmpty()) return true

    return listOf(country, region, town, locality.orEmpty())
        .any { part -> part.contains(other = normalizedQuery, ignoreCase = true) }
}
