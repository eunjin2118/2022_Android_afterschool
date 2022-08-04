package com.example.mimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mimo.MiMoCommunityFragment
import com.example.mimo.MiMoCreateFragment
import com.example.mimo.MiMoOpenChatFragment
import com.example.mimo.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var navigationView: NavigationView
    lateinit var drawerToggle : ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.drawer_nav_view)

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.community -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, MiMoCommunityFragment())
                        .commit()
                }
                R.id.create_text -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, MiMoCreateFragment())
                        .commit()
                }
                R.id.OpenChat -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame, MiMoOpenChatFragment())
                        .commit()
                }

            }

            drawerLayout.closeDrawers()

            true
        }

        drawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            R.string.drawer_open,
            R.string.drawer_close
        ) {}

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(drawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        drawerToggle.syncState()
    }

}

