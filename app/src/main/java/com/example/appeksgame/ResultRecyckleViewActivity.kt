package com.example.appeksgame

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.list

class ResultRecyckleViewActivity : AppCompatActivity() {
    lateinit var recycleView: RecyclerView
    lateinit var backButton: ImageButton
    lateinit var qqList: QuestionList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_recyckle_view)
        recycleView = findViewById(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(this)

      //  recycleView.adapter = ResultRecycleAdapter(this, QuestionList.questionList)
        backButton=findViewById(R.id.backButton)
        backButton.setOnClickListener{
            startGameActivity()
        }




    }
    fun startGameActivity()
    {
        val intent= Intent(this, MainActivity::class.java)
        startActivity(intent)


    }





}
