package com.example.roomexampleapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    val name : String,
    val age : String
){
    @PrimaryKey(autoGenerate = true) var id : Int = 0
}
