package com.m.sekoshin.myfinances.ui.addtransaction

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.FragmentAddTransactionBinding
import com.m.sekoshin.myfinances.util.*
import java.text.SimpleDateFormat
import java.util.*

class AddTransactionFragment : Fragment(R.layout.fragment_add_transaction) {

    private val TAG by lazy { "DEBUG: " + javaClass.simpleName }

    private val binding by viewBinding(FragmentAddTransactionBinding::bind)
    private var date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        binding.run {
            dateFormat = SimpleDateFormat("EEE, dd MMMM yyyy", Locale.getDefault())
            timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dateTime = date

            tvOperationDate.setOnClickListener {
                showDatePicker()
            }

            tvOperationTime.setOnClickListener {
                showTimePicker()
            }

            toolbarOperation.setupWithNavController(
                findNavController(),
                AppBarConfiguration(findNavController().graph)
            )

//            toolbar.setNavigationOnClickListener {
//                findNavController().navigateUp()
//            }
        }
    }

    private fun showTimePicker() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(getHour(date))
            .setMinute(getMinutes(date))
            .build()
        picker.addOnPositiveButtonClickListener {
            date = setTime(date, picker.hour, picker.minute)
            binding.dateTime = date
            binding.executePendingBindings()
        }
        activity?.let { picker.show(it.supportFragmentManager, picker.toString()) }
    }

    private fun showDatePicker() {
        val picker = MaterialDatePicker.Builder.datePicker()
            .setSelection(date.time)
            .build()
        picker.addOnPositiveButtonClickListener {
//            date.time = Date(it).time
            date = setDate(date, it)
            binding.dateTime = date
            binding.executePendingBindings()
        }
        activity?.let { picker.show(it.supportFragmentManager, picker.toString()) }
    }
}