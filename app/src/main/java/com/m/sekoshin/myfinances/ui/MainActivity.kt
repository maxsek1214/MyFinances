package com.m.sekoshin.myfinances.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.google.android.material.transition.MaterialSharedAxis
import com.m.sekoshin.myfinances.FinApp
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.ActivityMainBinding
import com.m.sekoshin.myfinances.ui.nav.BottomNavigationDrawerFragment
import com.m.sekoshin.myfinances.ui.nav.ChangeSettingsMenuStateAction
import com.m.sekoshin.myfinances.ui.settings.SettingsFragmentDirections
import com.m.sekoshin.myfinances.util.contentView

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener,
    NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

    private val TAG by lazy { "DEBUG: " + javaClass.simpleName }

//    private val _menuState = MutableStateFlow(true)
//    private val menuState: StateFlow<Boolean>
//        get() = _menuState

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    private val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.nav_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()

    private val bottomNavDrawer: BottomNavigationDrawerFragment by lazy(LazyThreadSafetyMode.NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavigationDrawerFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding.run {
            // Wrap binding.run to ensure ContentViewBindingDelegate is calling this Activity's
            // setContentView before accessing views
            getNavigation().addOnDestinationChangedListener(this@MainActivity)
        }

        binding.bottomAppBar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
            setOnMenuItemClickListener(this@MainActivity)
        }

        binding.fabAdd.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            setOnClickListener {
                showAddFragment()
            }
        }

        bottomNavDrawer.apply {
            addOnStateChangedAction(ChangeSettingsMenuStateAction {
//                _menuState.value = it
                binding.run {
                    Log.d(TAG, "menuState = {$it}")
                    if (bottomAppBar.visibility == View.VISIBLE) {
                        if (it) {
                            bottomAppBar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_hamburger_to_arrow)
                            fabAdd.hide()
                            bottomAppBar.replaceMenu(R.menu.bottomappbar_settings_menu)
                            (bottomAppBar.navigationIcon as AnimatedVectorDrawable).start()
                        } else {
                            bottomAppBar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_arrow_to_hamburger)

                            fabAdd.show()
                            bottomAppBar.replaceMenu(getBottomAppBarMenuForDestination())
                            (bottomAppBar.navigationIcon as AnimatedVectorDrawable).start()
                        }
                    }
                }
            })
            addNavigationListener(this@MainActivity)
        }

//        lifecycleScope.launch {
//            menuState.collect {
//
//            }
//        }
        (application as FinApp).preferenceRepository.themeModeLive.observe(this) { themeMode ->
            delegate.localNightMode = themeMode
        }
    }

    private fun showAddFragment() {
        hideBottomAppBar()
        val dest = getNavigation().currentDestination
        when (dest?.id) {
            R.id.transactionsFragment -> getNavigation().navigate(R.id.action_transactionsFragment_to_addTransactionFragment)
            R.id.accountsFragment -> getNavigation().navigate(R.id.action_accountsFragment_to_addAccountFragment)
        }
    }

    private fun showSettingsFragment() {
        hideBottomAppBar()
        currentNavigationFragment?.apply {
            exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                duration = resources.getInteger(R.integer.motion_duration_large).toLong()
            }
            reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                duration = resources.getInteger(R.integer.motion_duration_large).toLong()
            }
        }
        getNavigation().navigate(SettingsFragmentDirections.actionGlobalSettingsFragment())
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.transactionsFragment -> {
                showBottomAppBar()
                binding.bottomAppBar.replaceMenu(getBottomAppBarMenuForDestination(destination))
                Log.d(TAG, "starting transactionsFragment")
            }
            R.id.splashFragment -> {
                Log.d(TAG, "starting splashFragment")
            }
            R.id.settingsFragment -> {
                Log.d(TAG, "starting settingsFragment")
            }
            R.id.accountsFragment -> {
                showBottomAppBar()
                binding.bottomAppBar.replaceMenu(getBottomAppBarMenuForDestination(destination))
                Log.d(TAG, "starting accountsFragment")
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_accounts -> {
                Toast.makeText(this, getString(R.string.menu_accounts), Toast.LENGTH_SHORT).show()
                val dest = getNavigation().currentDestination

                if (dest?.id != R.id.accountsFragment) {

                    bottomNavDrawer.close()

                    currentNavigationFragment?.apply {
                        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
                            duration =
                                resources.getInteger(R.integer.motion_duration_large).toLong()
                        }
                        reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
                            duration =
                                resources.getInteger(R.integer.motion_duration_large).toLong()
                        }
                    }
                    getNavigation().navigate(R.id.action_transactionsFragment_to_accountsFragment)
                }
                item.isChecked = true
            }
//            R.id.menu_operations -> {
//                Toast.makeText(this, getString(R.string.menu_operations), Toast.LENGTH_SHORT).show()
//
//                bottomNavDrawer.close()
//
//                currentNavigationFragment?.apply {
//                    exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
//                        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
//                    }
//                    reenterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false).apply {
//                        duration = resources.getInteger(R.integer.motion_duration_large).toLong()
//                    }
//                }
//                getNavigation().navigate(TransactionsFragmentDirections.actionGlobalTransactionsFragment())
//            }
            R.id.menu_users -> Toast.makeText(
                this,
                getString(R.string.menu_users),
                Toast.LENGTH_SHORT
            ).show()
            R.id.menu_categories -> Toast.makeText(
                this,
                getString(R.string.menu_categories),
                Toast.LENGTH_SHORT
            ).show()
            R.id.menu_recipients -> Toast.makeText(
                this,
                getString(R.string.menu_recipients),
                Toast.LENGTH_SHORT
            ).show()
            R.id.menu_receipts -> Toast.makeText(
                this,
                getString(R.string.menu_receipts),
                Toast.LENGTH_SHORT
            ).show()
            R.id.menu_analytics -> Toast.makeText(
                this,
                getString(R.string.menu_analytics),
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    private fun showBottomAppBar() {
        binding.run {
            bottomAppBar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_arrow_to_hamburger)
            bottomAppBar.visibility = View.VISIBLE
            bottomAppBar.performShow()
            fabAdd.show()
            (bottomAppBar.navigationIcon as AnimatedVectorDrawable).start()
        }
    }

    private fun hideBottomAppBar() {
        binding.run {
            bottomAppBar.performHide()
            // Get a handle on the animator that hides the bottom app bar so we can wait to hide
            // the fab and bottom app bar until after it's exit animation finishes.
            bottomAppBar.animate().setListener(object : AnimatorListenerAdapter() {
                var isCanceled = false
                override fun onAnimationEnd(animation: Animator?) {
                    if (isCanceled) return
                    // Hide the BottomAppBar to avoid it showing above the keyboard
                    // when composing a new email.
                    bottomAppBar.visibility = View.GONE
                    fabAdd.visibility = View.INVISIBLE
                }

                override fun onAnimationCancel(animation: Animator?) {
                    isCanceled = true
                }
            })
        }
    }

    private fun getNavigation(): NavController {
        return findNavController(R.id.nav_fragment)
    }

    @MenuRes
    private fun getBottomAppBarMenuForDestination(destination: NavDestination? = null): Int {
        val dest = destination
            ?: getNavigation().currentDestination
        return when (dest?.id) {
            R.id.transactionsFragment -> R.menu.bottomappbar_transactions_menu
            else -> R.menu.bottomappbar_transactions_menu
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_bottomappbar_settings -> {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.menu_bottomappbar_settings),
                    Toast.LENGTH_SHORT
                ).show()
                bottomNavDrawer.toggle()
                showSettingsFragment()
            }
            R.id.menu_bottomappbar_transactions_filter -> Toast.makeText(
                this@MainActivity,
                getString(R.string.menu_bottomappbar_transactions_filter),
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }
}