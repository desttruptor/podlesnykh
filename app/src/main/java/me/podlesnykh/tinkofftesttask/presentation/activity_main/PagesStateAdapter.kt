package me.podlesnykh.tinkofftesttask.presentation.activity_main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.podlesnykh.tinkofftesttask.presentation.fragment_category.FragmentCategory
import me.podlesnykh.tinkofftesttask.presentation.models.Category

class PagesStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private val pages: List<Fragment> = listOf(
        FragmentCategory(Category.HOT),
        FragmentCategory(Category.LAST),
        FragmentCategory(Category.POPULAR),
        FragmentCategory(Category.RANDOM)
    )

    override fun getItemCount() = pages.size

    override fun createFragment(position: Int) = pages[position]
}