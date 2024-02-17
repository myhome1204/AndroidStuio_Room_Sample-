package com.example.roomexampleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import com.example.roomexampleapplication.database.User
import com.example.roomexampleapplication.database.UserDatabase
import com.example.roomexampleapplication.databinding.ActivityRoomExamBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RoomExamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRoomExamBinding
    private lateinit var db : UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomExamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = UserDatabase.get_instance(applicationContext)!!
        show()
        binding.btn1.setOnClickListener{
            update()
            show()
        }
    }
    private fun update() :Unit{
        var name = binding.name.text.toString()!!
        var age = binding.age.text.toString()
        CoroutineScope(Dispatchers.IO).launch {
            db.userdao().insert_user(User(name,age))
        }
    }
    private fun show(){
        var user_list = "유저 목록 \n"
        CoroutineScope(Dispatchers.Main).launch {
            val users = CoroutineScope(Dispatchers.IO).async {
                 db.userdao().get_all()
            }.await()
            for(user in users) {
                user_list += "이름 : ${user.name} 나이 : ${user.age}\n"
            }
            binding.result.text=user_list
        }
    }
}