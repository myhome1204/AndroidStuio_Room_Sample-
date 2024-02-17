package com.example.roomexampleapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class] , version = 1)
abstract  class UserDatabase : RoomDatabase() {
    abstract  fun userdao() : UserDao
    companion object{

        private var instance : UserDatabase? = null

        @Synchronized
        fun get_instance(context : Context) : UserDatabase? {
            if(instance ==null){
                synchronized(UserDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                    ).build()
                }
            }
            return instance
        }

    }
}