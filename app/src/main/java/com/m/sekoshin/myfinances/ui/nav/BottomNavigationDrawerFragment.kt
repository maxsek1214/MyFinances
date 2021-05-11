package com.m.sekoshin.myfinances.ui.nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.OnBackPressedCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.m.sekoshin.myfinances.R
import com.m.sekoshin.myfinances.databinding.FragmentBottomNavigationDrawerBinding

import kotlin.LazyThreadSafetyMode.NONE

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentBottomNavigationDrawerBinding

    private val behavior: BottomSheetBehavior<FrameLayout> by lazy(NONE) {
        from(binding.navBottomSheet)
    }

    private val bottomSheetCallback = BottomNavigationDrawerCallback()

    private val closeDrawerOnBackPressed = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            close()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, closeDrawerOnBackPressed)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBottomNavigationDrawerBinding.inflate(inflater, container, false)
        binding.navigationView.setOnApplyWindowInsetsListener { view, windowInsets ->
            // Record the window's top inset so it can be applied when the bottom sheet is slide up
            // to meet the top edge of the screen.
            view.setTag(
                R.id.tag_system_window_inset_top,
                windowInsets.systemWindowInsetTop
            )
            windowInsets
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {

            bottomSheetCallback.apply {
                // If the drawer is open, pressing the system back button should close the drawer.
                addOnStateChangedAction(object : OnStateChangedAction {
                    override fun onStateChanged(sheet: View, newState: Int) {
                        closeDrawerOnBackPressed.isEnabled = newState != STATE_HIDDEN
                    }
                })
                // Translating the navBottomSheet sheet
//                addOnSlideAction(ForegroundSheetTransformSlideAction(navigationView))
            }
            behavior.addBottomSheetCallback(bottomSheetCallback)
            behavior.state = STATE_HIDDEN
        }
    }

    fun open() {
        behavior.state = STATE_HALF_EXPANDED
    }

    fun close() {
        behavior.state = STATE_HIDDEN
    }

    fun toggle() {
        when (behavior.state) {
            STATE_HIDDEN -> open()
            STATE_HIDDEN,
            STATE_HALF_EXPANDED,
            STATE_EXPANDED,
            STATE_COLLAPSED -> close()
        }
    }

    fun addNavigationListener(listener: NavigationView.OnNavigationItemSelectedListener) {
        binding.navigationView.setNavigationItemSelectedListener(listener)
    }

    fun addOnSlideAction(action: OnSlideAction) {
        bottomSheetCallback.addOnSlideAction(action)
    }

    fun addOnStateChangedAction(action: OnStateChangedAction) {
        bottomSheetCallback.addOnStateChangedAction(action)
    }
}