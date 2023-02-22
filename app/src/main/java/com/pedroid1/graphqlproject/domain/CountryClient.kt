package com.pedroid1.graphqlproject.domain

import com.pedroid1.graphqlproject.domain.model.DetailedCountry
import com.pedroid1.graphqlproject.domain.model.SimpleCountry

interface CountryClient {
    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?
}