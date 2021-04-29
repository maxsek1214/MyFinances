package com.m.sekoshin.myfinances.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.m.sekoshin.myfinances.FinApp
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.FragmentSplashBinding
import com.m.sekoshin.myfinances.util.viewBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val TAG = "DEBUG: " + javaClass.simpleName

    private val binding by viewBinding(FragmentSplashBinding::bind)

    private lateinit var dbState: StateFlow<Boolean>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbState = (activity?.application as FinApp).database.getDBState()

        lifecycleScope.launch {
            (activity?.application as FinApp).database.accountTypeDao().getAccountTypes().count()
        }

        lifecycleScope.launchWhenCreated {
//            (activity?.application as FinApp).database.accountTypeDao().getAccountTypes().count()
            dbState.collect {
                if (it) {
                    Log.d(TAG, "DB is created [collect: $it]")
                    Handler().postDelayed({
                        context.let {
                            findNavController(this@SplashFragment).navigate(R.id.action_splashFragment_to_transactionsFragment)
                        }
                    }, 5000)
                } else Log.d(TAG, "DB is not created [collect: $it]")
            }
        }
        binding.executePendingBindings()
    }
}