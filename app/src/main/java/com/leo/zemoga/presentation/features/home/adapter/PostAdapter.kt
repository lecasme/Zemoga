package com.leo.zemoga.presentation.features.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leo.zemoga.R
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.presentation.utils.setOnSafeClickListener

class PostAdapter(private val posts: List<PostEntity>, private val listener: Listener) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    lateinit var context: Context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtId: TextView = view.findViewById(R.id.txtId)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtDescription: TextView = view.findViewById(R.id.txtDescription)
        val ivFavourite: ImageView = view.findViewById(R.id.imgFavourite)
        val ivDelete: ImageView = view.findViewById(R.id.imgTrash)
        val clPost: ConstraintLayout = view.findViewById(R.id.clPost)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        context = viewGroup.context
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_post,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val post = posts[position]

        viewHolder.txtId.text = post.id.toString()
        viewHolder.txtTitle.text = post.title
        viewHolder.txtDescription.text = post.body
        viewHolder.clPost.setOnSafeClickListener {
            listener.showDetails(post)
        }
        viewHolder.ivDelete.setOnSafeClickListener {
            listener.deletePost(post)
        }

        if(post.favourite){
            viewHolder.ivFavourite.visibility = View.VISIBLE
            viewHolder.ivFavourite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favourite_on))
        }else{
            viewHolder.ivFavourite.visibility = View.GONE
        }

    }

    override fun getItemCount() = posts.size

    interface Listener {
        fun showDetails(post: PostEntity)
        fun deletePost(post: PostEntity)
    }

}