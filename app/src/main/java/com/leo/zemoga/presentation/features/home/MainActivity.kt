package com.leo.zemoga.presentation.features.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.leo.zemoga.R
import com.leo.zemoga.databinding.ActivityMainBinding
import com.leo.zemoga.presentation.features.home.adapter.SectionsPagerAdapter
import com.leo.zemoga.presentation.utils.showSnackbar
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<HomeViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "You won't regret if you hire me", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        setSupportActionBar(binding.toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuDelete -> {
                viewModel.removeAllPosts()
                true
            }
            R.id.menuReload -> {
                viewModel.fetchPosts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}