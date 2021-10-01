package com.example.appeksgame

import Question
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appeksgame.QuestionList

class ResultRecyckleViewActivity : AppCompatActivity() {
    lateinit var recycleView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_recyckle_view)
        recycleView = findViewById(R.id.recycleView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = ResultRecycleAdapter(this, QuestionList.usedQuestion)



    }




}
