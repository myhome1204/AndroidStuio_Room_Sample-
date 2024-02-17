package com.example.roomexampleapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.nio.channels.SelectableChannel

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert_user(user : User)

    @Query("SELECT * FROM User")
    fun get_all() : List<User>
}