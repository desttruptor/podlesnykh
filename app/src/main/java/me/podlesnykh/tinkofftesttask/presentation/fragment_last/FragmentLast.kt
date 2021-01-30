package me.podlesnykh.tinkofftesttask.presentation.fragment_last

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.podlesnykh.tinkofftesttask.R

class FragmentLast : Fragment() {

    private lateinit var viewModelFragment: FragmentLastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }
}