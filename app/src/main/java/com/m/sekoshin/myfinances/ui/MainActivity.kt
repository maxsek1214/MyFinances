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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.ActivityMainBinding
import com.m.sekoshin.myfinances.ui.nav.BottomNavigationDrawerFragment
import com.m.sekoshin.myfinances.ui.nav.ChangeSettingsMenuStateAction
import com.m.sekoshin.myfinances.util.contentView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener,
        NavigationView.OnNavigationItemSelectedListener, Toolbar.OnMenuItemClickListener {

    private val TAG = "DEBUG: " + javaClass.simpleName

//    private val _menuState = MutableStateFlow(true)
//    private val menuState: StateFlow<Boolean>
//        get() = _menuState

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)

    private val bottomNavDrawer: BottomNavigationDrawerFragment by lazy(LazyThreadSafetyMode.NONE) {
        supportFragmentManager.findFragmentById(R.id.bottom_nav_drawer) as BottomNavigationDrawerFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.bottomAppBar.apply {
            setNavigationOnClickListener {
                bottomNavDrawer.toggle()
            }
            setOnMenuItemClickListener(this@MainActivity)
        }

        binding.fabAdd.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
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
                            bottomAppBar.replaceMenu(R.menu.bottomappbar_transactions_menu)//getBottomAppBarMenuForDestination())
                            (bottomAppBar.navigationIcon as AnimatedVectorDrawable).start()
                        }
                    }
                }
            })
            addNavigationListener(this@MainActivity)
        }

        binding.run {
            findNavController(R.id.nav_fragment).addOnDestinationChangedListener(this@MainActivity)
        }

//        lifecycleScope.launch {
//            menuState.collect {
//
//            }
//        }
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
//                hideBottomAppBar()
                Log.d(TAG, "starting splashFragment")
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_accounts -> Toast.makeText(
                    this,
                    getString(R.string.menu_accounts),
                    Toast.LENGTH_SHORT
            ).show()
            R.id.menu_operations -> Toast.makeText(
                    this,
                    getString(R.string.menu_operations),
                    Toast.LENGTH_SHORT
            ).show()
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
            bottomAppBar.visibility = View.VISIBLE
            bottomAppBar.performShow()
            fabAdd.show()
        }
    }

    private fun hideBottomAppBar() {
        binding.run {
//            fabAdd.hide()
            bottomAppBar.performHide();
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

    @MenuRes
    private fun getBottomAppBarMenuForDestination(destination: NavDestination? = null): Int {
        val dest = destination
                ?: findNavController(R.id.nav_host_fragment_container).currentDestination
        return when (dest?.id) {
            R.id.transactionsFragment -> R.menu.bottomappbar_transactions_menu
            else -> R.menu.bottomappbar_transactions_menu
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_bottomappbar_settings -> Toast.makeText(this@MainActivity, getString(R.string.menu_bottomappbar_settings), Toast.LENGTH_SHORT).show()
            R.id.menu_bottomappbar_transactions_filter -> Toast.makeText(this@MainActivity, getString(R.string.menu_bottomappbar_transactions_filter), Toast.LENGTH_SHORT).show()
        }
        return true
    }
}