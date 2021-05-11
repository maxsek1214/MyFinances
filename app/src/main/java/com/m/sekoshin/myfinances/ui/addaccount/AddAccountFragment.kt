package com.m.sekoshin.myfinances.ui.addaccount

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.FragmentAddAccountBinding
import com.m.sekoshin.myfinances.util.viewBinding

class AddAccountFragment : Fragment(R.layout.fragment_add_account) {

    private val TAG by lazy { "DEBUG: " + javaClass.simpleName }

    private val binding by viewBinding(FragmentAddAccountBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        binding.run {
            toolbarAccount.setupWithNavController(findNavController(), AppBarConfiguration(findNavController().graph))
        }
    }
}