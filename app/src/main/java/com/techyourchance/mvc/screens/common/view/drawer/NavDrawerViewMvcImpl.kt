package com.techyourchance.mvc.screens.common.view.drawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.view.BaseObservableViewMvc

class NavDrawerViewMvcImpl(
        inflater: LayoutInflater,
        parent: ViewGroup?
):
        BaseObservableViewMvc<NavDrawerViewMvc.Listener>(),
        NavDrawerViewMvc {

    override val rootView: View = inflater.inflate(R.layout.layout_drawer, parent, false)

    private val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    private val contentPlaceholder: FrameLayout = findViewById(R.id.content_placeholder)
    private val navigationView: NavigationView = findViewById(R.id.navigation_view)

    init {
        navigationView.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawers()
            when(item.itemId) {
                R.id.drawer_menu_questions -> {
                    getListeners().forEach {
                        it.onQuestionsClicked()
                    }
                }
            }
            false
        }
    }

    override val fragmentFrame: FrameLayout = contentPlaceholder

    override fun isDrawerOpen(): Boolean = drawerLayout.isDrawerOpen(GravityCompat.START)

    override fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun closeDrawer() {
        drawerLayout.closeDrawers()
    }
}