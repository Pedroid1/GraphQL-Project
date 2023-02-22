package com.pedroid1.graphqlproject.domain.use_case

import com.pedroid1.graphqlproject.domain.CountryClient
import com.pedroid1.graphqlproject.domain.model.DetailedCountry
import com.pedroid1.graphqlproject.domain.model.SimpleCountry

class GetCountryUseCase(
    private val countryClient: CountryClient
) {
    suspend fun execute(code: String) : DetailedCountry? {
        return countryClient.getCountry(code)
    }
}