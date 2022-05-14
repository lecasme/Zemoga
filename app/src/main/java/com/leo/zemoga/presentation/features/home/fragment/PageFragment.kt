package com.leo.zemoga.presentation.features.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.databinding.FragmentMainBinding
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.presentation.commons.BaseFragment
import com.leo.zemoga.presentation.commons.BaseViewModel
import com.leo.zemoga.presentation.features.details.DetailsActivity
import com.leo.zemoga.presentation.features.home.adapter.PostAdapter
import com.leo.zemoga.presentation.utils.toModel
import org.koin.android.ext.android.inject

class PageFragment : BaseFragment(), PostAdapter.Listener {

    private val viewModel by inject<PageViewModel>()
    lateinit var binding: FragmentMainBinding
    private var postAdapter: PostAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when(arguments?.getInt(ARG_SECTION_NUMBER) ?: 2){
            SECTION_FAVOURITE -> viewModel.getPosts(true)
            SECTION_ALL -> viewModel.getPosts(false)
        }

        viewModel.posts.observe(viewLifecycleOwner) {
            it?.let {
                postAdapter = PostAdapter(it, this)
                binding.rcvPosts.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = postAdapter
                }
            }
        }
    }

    override fun showDetails(post: PostEntity) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.EXTRA_POST, post.toModel())
        startActivity(intent)
    }

    override fun deletePost(post: PostEntity) {
        viewModel.deletePost(post)
    }

    override fun getViewModel(): BaseViewModel = viewModel

    companion object {

        private const val ARG_SECTION_NUMBER = "section_number"
        private const val SECTION_ALL = 1
        private const val SECTION_FAVOURITE = 2

        @JvmStatic
        fun newInstance(sectionNumber: Int): PageFragment {
            return PageFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}