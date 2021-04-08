package com.mayank_amr.friendsfeed.splashscreen.ui

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mayank_amr.friendsfeed.R
import com.mayank_amr.friendsfeed.splashscreen.viewmodel.SplashScreenViewModel
import com.mayank_amr.friendsfeed.userauth.data.UserPreferences
import kotlinx.coroutines.launch

class SplashScreenFragment : Fragment() {
    private lateinit var userPreferences: UserPreferences


    companion object {
        fun newInstance() = SplashScreenFragment()
    }


    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_screen_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(SplashScreenViewModel::class.java)
        userPreferences = UserPreferences(requireContext())
        waitToStart()


    }


    private fun goToLoginPage() {
        findNavController().navigate(
            R.id.action_splashScreenFragment_to_loginFragment,
            null,
            null,
            null
        )
    }


    private fun goToHomePage() {
        findNavController().navigate(
            R.id.action_splashScreenFragment_to_homeFragment,
            null,
            null,
            null
        )
    }


    private fun waitToStart() {
        val handler = Handler()
        handler.postDelayed({
            launchFragment()
        }, 2000)
    }

    private fun launchFragment() {
        lifecycleScope.launch {
            userPreferences.authToken.asLiveData().observe(viewLifecycleOwner, {
                if (it == null) {
                    goToLoginPage()
                } else {
                    goToHomePage()
                }
            })
        }
    }

}