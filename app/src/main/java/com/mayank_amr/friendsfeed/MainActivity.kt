package com.mayank_amr.friendsfeed

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mayank_amr.friendsfeed.network.ConnectionLiveData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, { isNetworkAvailable ->
            // update ui for internet connection
            if (isNetworkAvailable) {
                Toast.makeText(this, "Online", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Offline", Toast.LENGTH_LONG).show()

            }
        })

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHostFragment.findNavController()


        /*-----------*** Configuring appBar for same level destination ***------------------------*/
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.searchFragment,
                R.id.createPostFragment, R.id.favouriteFragment, R.id.meFragment
            ) //, drawer_layout_main  // It enables hamburger icon in main toolbar and animate for back arrow.
        )


        setSupportActionBar(main_activity_toolbar)  // Setting Action bar

        setupActionBarWithNavController(
            navController,                          // Attaching navController with actionbar
            appBarConfiguration                     // Attaching appBarConfiguration with actionbar
        )

        /*-----------------------*** Connecting BottomNavigationView to navController ***---------*/
        bottom_nav_main_activity.setupWithNavController(navController)

        /*-----------------------*** Connecting Navigation drawer to navController ***------------*/
        navigation_drawer_main_activity.setupWithNavController(navController)


        /*-----------------------*** To Hide and show BottomNav in these Fragment only ***--------*/
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.searchFragment -> showBottomNav()
                R.id.createPostFragment -> showBottomNav()
                R.id.favouriteFragment -> showBottomNav()
                R.id.meFragment -> showBottomNav()
                R.id.splashScreenFragment -> {
                    //hideToolbar()
                    hideBottomNav()
                }
                else -> hideBottomNav()
            }
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawer_layout_main.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_main.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    /*-----------*** Show the Bottom Bar ***------------------------------------------------------*/
    private fun showBottomNav() {
        bottom_nav_main_activity.visibility = View.VISIBLE

    }

    /*-----------*** Hide the Bottom Bar ***------------------------------------------------------*/
    private fun hideBottomNav() {
        bottom_nav_main_activity.visibility = View.GONE

    }

    /*-----------*** Hide the Tool Bar ***--------------------------------------------------------*/
    private fun hideToolbar() {
        main_activity_toolbar.visibility = View.GONE
    }

    /*-----------*** Show the Tool Bar ***--------------------------------------------------------*/
    private fun showToolbar() {
        main_activity_toolbar.visibility = View.VISIBLE
    }
}