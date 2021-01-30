package me.podlesnykh.tinkofftesttask.presentation.activity_main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.podlesnykh.tinkofftesttask.presentation.fragment_hot.FragmentHot
import me.podlesnykh.tinkofftesttask.presentation.fragment_last.FragmentLast
import me.podlesnykh.tinkofftesttask.presentation.fragment_last.FragmentLastViewModel
import me.podlesnykh.tinkofftesttask.presentation.fragment_popular.FragmentPopular

class PagesStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pages: List<Fragment> = listOf(
        FragmentHot(),
        FragmentLast(),
        FragmentPopular()
    )

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int) = pages[position]

}