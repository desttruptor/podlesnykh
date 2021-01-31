package me.podlesnykh.tinkofftesttask.presentation.activity_main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import me.podlesnykh.tinkofftesttask.R
import me.podlesnykh.tinkofftesttask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initViewPager()
    }

    private fun initViewPager() {
        val adapter = PagesStateAdapter(supportFragmentManager, lifecycle)
        val names = listOf(
            getString(R.string.tab_label_hot),
            getString(R.string.tab_label_last),
            getString(R.string.tab_label_popular),
            getString(R.string.tab_label_random)
        )
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewpager) { tab, position ->
            tab.text = names[position]
        }.attach()

    }
}
