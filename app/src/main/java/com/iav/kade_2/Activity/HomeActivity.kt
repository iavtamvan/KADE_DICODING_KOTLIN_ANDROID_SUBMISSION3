package com.iav.kade_2.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.iav.kade_2.Fragment.FavoriteFragment
import com.iav.kade_2.Fragment.HomeFragment
import com.iav.kade_2.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                home()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                fav()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun fav() {
        val transaction = fragmentManager.beginTransaction()
        val fragment = FavoriteFragment()
        transaction.replace(R.id.frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun home() {

        val transaction = fragmentManager.beginTransaction()
        val fragment = HomeFragment()
        transaction.replace(R.id.frame,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        home()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
