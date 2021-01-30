package me.podlesnykh.tinkofftesttask.presentation.fragment_hot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.podlesnykh.tinkofftesttask.R

class FragmentHot : Fragment() {

    private lateinit var viewModel: FragmentHotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

}