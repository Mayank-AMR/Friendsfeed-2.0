package com.mayank_amr.friendsfeed

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mayank_amr.friendsfeed.network.ConnectionLiveData

class MainActivity : AppCompatActivity() {
    lateinit var connectionLiveData: ConnectionLiveData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, { isNetworkAvailable ->
            // update ui
            if (isNetworkAvailable) {
                Toast.makeText(this, "Online", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Offline", Toast.LENGTH_LONG).show()

            }
        })


    }
}