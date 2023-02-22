package com.pedroid1.graphqlproject.ui

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.WindowManager
import com.pedroid1.graphqlproject.databinding.CustomDialogBinding
import com.pedroid1.graphqlproject.domain.model.DetailedCountry

class CustomDialog(context: Context) : Dialog(context) {
    private var _binding: CustomDialogBinding

    init {
        _binding = CustomDialogBinding.inflate(LayoutInflater.from(context))
        setContentView(_binding.root)
        setCancelable(true)
    }

    fun setCountry(detailedCountry: DetailedCountry) {
        _binding.country = detailedCountry
        detailedCountry.languages.firstOrNull()?.let {
            _binding.txtLanguage.text = it
        }
    }

    override fun onStart() {
        super.onStart()
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}