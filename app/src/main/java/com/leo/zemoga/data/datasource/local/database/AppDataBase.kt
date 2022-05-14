package com.leo.zemoga.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leo.zemoga.data.datasource.local.database.dao.PostDao
import com.leo.zemoga.data.entity.PostEntity

@Database(
    entities = [
        PostEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    // DAO
    abstract fun postDao(): PostDao

    companion object {
        private const val databaseName = "zemoga.db"
        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, databaseName).fallbackToDestructiveMigration().build()
    }
}