package com.pedroid1.graphqlproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.pedroid1.graphqlproject.R
import com.pedroid1.graphqlproject.databinding.ActivityMainBinding
import com.pedroid1.graphqlproject.domain.model.DetailedCountry
import com.pedroid1.graphqlproject.domain.model.SimpleCountry
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), CountriesAdapter.OnSelectCountry {

    private lateinit var _binding: ActivityMainBinding
    private val viewModel: CountriesViewModel by lazy { ViewModelProvider(this)[CountriesViewModel::class.java] }
    private lateinit var dialog: CustomDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        dialog = CustomDialog(this)
        dialog.setOnDismissListener {
            viewModel.dismissCountryDialog()
        }
        observers()
    }

    private fun observers() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.state.collectLatest {
                    handleCountriesState(it)
                }
            }
            launch {
                viewModel.detailedCountry.collectLatest { detailedCountry ->
                    detailedCountry?.let { showCountryDialog(detailedCountry) }
                }
            }
        }
    }

    private fun handleCountriesState(state: CountriesState) {
        state.apply {
            _binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.INVISIBLE
            prepareRecyclerView(countries)
        }
    }

    private fun showCountryDialog(country: DetailedCountry) {
        dialog.setCountry(country)
        dialog.show()
    }

    private fun prepareRecyclerView(countries: List<SimpleCountry>) {
        val adapter = CountriesAdapter(countries, this)
        _binding.rvCountries.adapter = adapter
    }

    override fun onSelected(code: String) {
        viewModel.selectCountry(code)
    }
}