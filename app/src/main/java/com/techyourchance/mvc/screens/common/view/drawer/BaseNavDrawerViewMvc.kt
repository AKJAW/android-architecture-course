package com.techyourchance.mvc.screens.common.view.drawer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.techyourchance.mvc.R
import com.techyourchance.mvc.screens.common.view.BaseObservableViewMvc

abstract class BaseNavDrawerViewMvc<Listener>(
        @LayoutRes contentLayoutId: Int,
        inflater: LayoutInflater,
        parent: ViewGroup?
): BaseObservableViewMvc<Listener>(){

    override val rootView: View = inflater.inflate(R.layout.layout_drawer, parent, false)

    private val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
    private val contentPlaceholder: FrameLayout = findViewById(R.id.content_placeholder)
    private val navigationView: NavigationView = findViewById(R.id.navigation_view)

    init {
        val content = inflater.inflate(contentLayoutId, parent, false)
        contentPlaceholder.addView(content)

        navigationView.setNavigationItemSelectedListener { item ->
            drawerLayout.closeDrawers()
            when(item.itemId) {
                R.id.drawer_menu_questions -> onDrawerItemClicked(DrawerItem.QUESTIONS)
            }
            false
        }
    }

    protected abstract fun onDrawerItemClicked(drawerItem: DrawerItem)

    protected fun showDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }
}