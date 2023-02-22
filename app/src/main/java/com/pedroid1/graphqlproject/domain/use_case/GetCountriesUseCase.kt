package com.pedroid1.graphqlproject.domain.use_case

import com.pedroid1.graphqlproject.domain.CountryClient
import com.pedroid1.graphqlproject.domain.model.SimpleCountry

class GetCountriesUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute() : List<SimpleCountry> {
        return countryClient.getCountries()
            .sortedBy { it.name }
    }
}