package com.leo.zemoga.data.datasource.local

import androidx.lifecycle.LiveData
import com.leo.zemoga.data.datasource.local.database.dao.PostDao
import com.leo.zemoga.data.entity.PostEntity
import com.leo.zemoga.domain.models.Post
import com.leo.zemoga.presentation.utils.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PostLocalDataSource {
    suspend fun selectPosts(favourite: Boolean): LiveData<List<PostEntity>?>
    suspend fun insertPosts(posts : List<PostEntity>)
    suspend fun deletePost(post: PostEntity)
    suspend fun deleteAllPosts()
    suspend fun updatePost(post: Post)
}

class PostLocalDataSourceImpl(
    private val moviesDao: PostDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PostLocalDataSource {

    override suspend fun selectPosts(favourite: Boolean): LiveData<List<PostEntity>?> {
        return if(favourite){
            moviesDao.selectPostsFavourites()
        }else{
            moviesDao.selectPosts()
        }
    }

    override suspend fun insertPosts(posts : List<PostEntity>): Unit = withContext(ioDispatcher) {
        moviesDao.insertPosts(posts)
    }

    override suspend fun deletePost(post: PostEntity) {
        moviesDao.delete(post)
    }

    override suspend fun deleteAllPosts() {
        moviesDao.deleteAllPosts()
    }

    override suspend fun updatePost(post: Post) {
        moviesDao.update(post.toEntity())
    }

}