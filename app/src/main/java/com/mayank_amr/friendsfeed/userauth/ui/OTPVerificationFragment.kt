package com.mayank_amr.friendsfeed.userauth.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mayank_amr.friendsfeed.R
import com.mayank_amr.friendsfeed.userauth.viewmodel.OTPVerificationViewModel

class OTPVerificationFragment : Fragment() {

    companion object {
        fun newInstance() = OTPVerificationFragment()
    }

    private lateinit var viewModel: OTPVerificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.o_t_p_verification_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OTPVerificationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}