package me.podlesnykh.tinkofftesttask.presentation.fragment_hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import me.podlesnykh.tinkofftesttask.databinding.FragmentPostBinding

class FragmentHot : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FragmentHotViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}