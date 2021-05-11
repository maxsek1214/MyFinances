package com.m.sekoshin.myfinances.ui.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.m.sekoshin.myfinances.FinApp
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.FragmentSplashBinding
import com.m.sekoshin.myfinances.util.viewBinding
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private val TAG by lazy { "DEBUG: " + javaClass.simpleName }

    private val binding by viewBinding(FragmentSplashBinding::bind)

    private lateinit var dbState: StateFlow<Boolean>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")
        dbState = getFinApp().database.getDBState()

        lifecycleScope.launch {
//            (activity?.application as FinApp).database.accountTypeDao().getAccountTypes().count()
            dbState.collect {
                if (it) {
                    Log.d(TAG, "DB is created [collect: $it]")
                    Handler().postDelayed({
                        activity?.findNavController(R.id.nav_fragment)?.navigate(R.id.action_splashFragment_to_transactionsFragment)
                    }, 1000)
                } else Log.d(TAG, "DB is not created [collect: $it]")
            }
        }
        binding.executePendingBindings()

        lifecycleScope.launchWhenStarted {
            getFinApp().database.accountTypeDao().getAccountTypes().count()
        }
    }

    private fun getFinApp(): FinApp {
        return activity?.application as FinApp
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}