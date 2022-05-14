package com.leo.zemoga.data.datasource.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leo.zemoga.data.entity.PostEntity
import retrofit2.http.DELETE

@Dao
abstract class PostDao : BaseDao<PostEntity> {

    @Query("SELECT * FROM posts ORDER BY favourite DESC")
    abstract fun selectPosts(): LiveData<List<PostEntity>?>

    @Query("SELECT * FROM posts WHERE favourite = 1 ORDER BY favourite DESC")
    abstract fun selectPostsFavourites(): LiveData<List<PostEntity>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertPosts(obj: List<PostEntity>)

    @Query("DELETE FROM posts")
    abstract suspend fun deleteAllPosts()

}