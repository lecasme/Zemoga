package com.leo.zemoga.presentation.features.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.leo.zemoga.R
import com.leo.zemoga.databinding.ActivityDetailsBinding
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.domain.models.User
import com.leo.zemoga.presentation.commons.BaseActivity
import com.leo.zemoga.presentation.commons.BaseViewModel
import org.koin.android.ext.android.inject

class DetailsActivity : BaseActivity() {

    private val viewModel by inject<DetailsViewModel>()
    lateinit var binding: ActivityDetailsBinding
    private var post: Post? = null
    private var menu: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        (intent.extras?.get(EXTRA_POST) as Post).let {
            post = it
            setUpPost()
        }

        viewModel.user.observe(this) {
            it?.let { setUpUser(it) }
        }

    }

    private fun setUpPost(){
        post?.let {
            binding.txtTitle.text = it.title
            binding.txtDescription.text = it.body
            viewModel.getUser(it.id)
        }
    }

    private fun setUpUser(user: User){
        binding.groupUser.visibility = View.VISIBLE
        binding.txtUser.text = user.name
        binding.txtEmail.text = user.email
        binding.txtPhone.text = user.phone
        binding.txtWebsite.text = user.website

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu
        changeMenuStar(post?.favourite ?: false)
        return true
    }

    private fun changeMenuStar(status: Boolean){
        if(status){
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourite_on)
        }else{
            menu?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favourite_off)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuFavourite -> {
                post?.let {
                    it.favourite = !it.favourite
                    changeMenuStar(it.favourite)
                    viewModel.updatePost(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object{
        const val EXTRA_POST = "extra-post"
    }

}
