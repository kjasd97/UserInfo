package com.ulyanenko.userinfo.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [GitHubUserEntity::class, UserRepositoryEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun gitHubUserDao(): GitHubUserDao
    abstract fun userRepositoryDao(): UserRepositoryDao



    companion object {


        private var database: AppDataBase? = null

        fun getInstance(application: Application): AppDataBase {
            database?.let {
                return it
            }

            val db = Room.databaseBuilder(
                application,
                AppDataBase::class.java,
                "movie_db"
            ).fallbackToDestructiveMigration().build()
            database = db
            return database as AppDataBase
        }
    }
}