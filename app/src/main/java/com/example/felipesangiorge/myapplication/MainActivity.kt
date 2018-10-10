package com.example.felipesangiorge.myapplication

import android.content.Intent
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.felipesangiorge.myapplication.database.DbWorkerThread
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.app_main_content,MapFragment(),"MAP_FRAGMENT")
            .commit()

        bottom_menu.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.action_home ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.app_main_content,MapFragment(),"MAP_FRAGMENT")
                        .commit()
                    true
                }
                R.id.action_dashboard ->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.app_main_content,DashboardFragment(),"DASHBOARD_FRAGMENT")
                        .commit()
                        true
                }
                R.id.action_me ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.app_main_content,MyPerfilFragment(),"MYPERFIL_FRAGMENT")
                            .commit()
                        true
                }

                else ->{
                false
            }

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
