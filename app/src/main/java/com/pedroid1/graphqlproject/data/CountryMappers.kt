package com.pedroid1.graphqlproject.data

import com.pedroid1.CountriesQuery
import com.pedroid1.CountryQuery
import com.pedroid1.graphqlproject.domain.model.DetailedCountry
import com.pedroid1.graphqlproject.domain.model.SimpleCountry

fun CountryQuery.Country.toDetailedCountry() = DetailedCountry(
    code,
    name,
    emoji,
    capital ?: "No capital",
    currency ?: "No currency",
    languages.mapNotNull { it.name },
    continent.name
)

fun CountriesQuery.Country.toSimplesCountry() = SimpleCountry(
    code,
    name,
    emoji,
    capital ?: "No capital"
)