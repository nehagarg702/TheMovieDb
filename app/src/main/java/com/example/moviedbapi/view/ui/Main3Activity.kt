package com.example.moviedbapi.view.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviedbapi.R
import com.example.moviesdbapi.view.ui.FirstFragment
import com.example.moviesdbapi.view.ui.SortByFragmeny
import com.google.android.material.navigation.NavigationView

class Main3Activity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    var home=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        openFragment("popularity.desc","Popularity")
        navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(1))
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.popularity -> {
                    if(!navView.menu.getItem(0).subMenu.getItem(1).isChecked) {
                        openFragment("popularity.desc", "Popularity")
                        navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(1))
                    }
                }
                R.id.vote_Average -> {
                    if(!navView.menu.getItem(0).subMenu.getItem(3).isChecked) {
                        openFragment("vote_average.desc", "Vote Average")
                        navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(3))
                    }
                }
                R.id.vote_count-> {
                    if(!navView.menu.getItem(0).subMenu.getItem(2).isChecked) {
                        openFragment("vote_count.desc", "Vote Count")
                        navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(2))
                    }
                }
                R.id.Title -> {
                    if(!navView.menu.getItem(0).subMenu.getItem(0).isChecked)
                    openFragment("original_title.desc", "Title")
                    navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(0))
                }
                R.id.Revenue -> {
                    if(!navView.menu.getItem(0).subMenu.getItem(0).isChecked)
                    openFragment("revenue.desc", "Revenue")
                    navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(5))

                }
                R.id.release_date-> {
                    if(!navView.menu.getItem(0).subMenu.getItem(0).isChecked)
                    openFragment("release_date.desc", "Release Date")
                    navView.setCheckedItem(navView.menu.getItem(0).subMenu.getItem(4))
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

    }

    private fun openFragment(sortBy: String,title : String)
    {
        supportActionBar!!.title=title
        val fragment=SortByFragmeny()
        val bundle=Bundle()
        bundle.putString("sortBy",sortBy)
        fragment.arguments=bundle
        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, fragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

}