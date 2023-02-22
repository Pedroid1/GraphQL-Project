package com.pedroid1.graphqlproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedroid1.graphqlproject.domain.model.DetailedCountry
import com.pedroid1.graphqlproject.domain.model.SimpleCountry
import com.pedroid1.graphqlproject.domain.use_case.GetCountriesUseCase
import com.pedroid1.graphqlproject.domain.use_case.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor (
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    private val _detailedCountry = MutableStateFlow<DetailedCountry?>(null)
    val detailedCountry = _detailedCountry.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            _state.update {
                it.copy(
                    countries = getCountriesUseCase.execute(),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _detailedCountry.value = getCountryUseCase.execute(code)
        }
    }

    fun dismissCountryDialog() {
        _detailedCountry.value = null
    }

}

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean = false
)