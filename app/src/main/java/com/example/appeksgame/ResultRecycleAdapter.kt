package com.example.appeksgame

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.nio.file.Files.size
import com.example.appeksgame.QuestionList as AppeksgameQuestionList

class ResultRecycleAdapter(val context: Context, val qList: List<Question>) :


    RecyclerView.Adapter<ResultRecycleAdapter.ViewHolder>() {
    val latoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("adapter!!!","onCreateViewHolder körs")

        val itemView = latoutInflater.inflate(R.layout.list_result, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = qList[position]
        holder.questionReviewTextView.text = question.questionText



        Log.d("adapter!!!","onBindViewHolder körs")
        when (question.currectAnswer.size){
            1 -> holder.qAnswerImageButton1.setBackgroundResource(question.currectAnswer[0])
            2 -> {
                holder.qAnswerImageButton1.setBackgroundResource(question.currectAnswer[0])
                holder.qAnswerImageButton2.setBackgroundResource(question.currectAnswer[1])
            }

            3 ->
            {
                for (a in question.currectAnswer)
                    Log.d("???","${question.currectAnswer[a]} number of Image")
                holder.qAnswerImageButton1.setBackgroundResource(question.currectAnswer[0])
                holder.qAnswerImageButton2.setBackgroundResource(question.currectAnswer[1])
                holder.qAnswerImageButton3.setBackgroundResource(question.currectAnswer[2])

            }
            4 ->
            {

                holder.qAnswerImageButton1.setBackgroundResource(question.currectAnswer[0])
                holder.qAnswerImageButton2.setBackgroundResource(question.currectAnswer[1])
                holder.qAnswerImageButton3.setBackgroundResource(question.currectAnswer[2])
                holder.qAnswerImageButton4.setBackgroundResource(question.currectAnswer[3])

            }


        }



        holder.userAnswerTextView.text = question.questionText


    }

    override fun getItemCount(): Int {
        Log.d("adapter!!!","getItemCount körs ${qList.size}" )


        return qList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionReviewTextView = itemView.findViewById<TextView>(R.id.questionReviewTextView)
        val qAnswerImageButton1 = itemView.findViewById<ImageButton>(R.id.qAnswerImageButton1)
        val qAnswerImageButton2 = itemView.findViewById<ImageButton>(R.id.qAnswerImageButton2)
        val qAnswerImageButton3 = itemView.findViewById<ImageButton>(R.id.qAnswerImageButton3)
        val qAnswerImageButton4 = itemView.findViewById<ImageButton>(R.id.qAnswerImageButton4)
        val userAnswerTextView = itemView.findViewById<TextView>(R.id.userAnswerTextView)


    }
}