package com.example.appeksgame


import Question
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.logging.LogManager
import android.view.View
import androidx.core.view.postDelayed


class StartGameActivity : AppCompatActivity() {
    lateinit var scoreTextView : TextView
    lateinit var questionTextView: TextView
    lateinit var answerButton1 : ImageButton
    lateinit var answerButton2 : ImageButton
    lateinit var answerButton3 : ImageButton
    lateinit var answerButton4 : ImageButton
    lateinit var answerButton5 : ImageButton
    lateinit var answerButton6 : ImageButton
    lateinit var answerButton7 : ImageButton
    lateinit var answerButton8 : ImageButton
    lateinit var answerButton9 : ImageButton
    lateinit var nextQuestionButton : Button
    var currentQuestion :Question? = null
    val questionList = QuestionList()
    var numberOfAnswer : Int =1





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        scoreTextView = findViewById(R.id.scoreTextView)
        scoreTextView.text="0"
        questionTextView = findViewById(R.id.questionTextView)
        answerButton1= findViewById(R.id.answerButton1) as ImageButton
        answerButton2= findViewById(R.id.answerButton2)
        answerButton3= findViewById(R.id.answerButton3)
        answerButton4= findViewById(R.id.answerButton4)
        answerButton5= findViewById(R.id.answerButton5)
        answerButton6= findViewById(R.id.answerButton6)
        answerButton7= findViewById(R.id.answerButton7)
        answerButton8= findViewById(R.id.answerButton8)
        answerButton9= findViewById(R.id.answerButton9)
        nextQuestionButton =findViewById(R.id.nextQuestionButton)



        loadNewQuestion ()

       nextQuestionButton.setOnClickListener(){
           loadNewQuestion ()

       }
       /* answerButton1.setOnClickListener{

            checkAnswer()
            Log.d("check","button pressed")

        }

        */
        answerButton1.apply {
            setOnDebouncedClickListener {
                checkAnswer()

            }
        }


    }
    class OnDebouncedClickListener(private val delayInMilliSeconds: Long, val action: () -> Unit) : View.OnClickListener {
        var enable = true

        override fun onClick(view: View?) {
            if (enable) {
                enable = false
                view?.postDelayed(delayInMilliSeconds) { enable = true }
                action()
            }
        }
    }

    fun View.setOnDebouncedClickListener(delayInMilliSeconds: Long = 10000, action: () -> Unit) {
        val onDebouncedClickListener = OnDebouncedClickListener(delayInMilliSeconds, action)
        setOnClickListener(onDebouncedClickListener)
    }
    fun loadNewQuestion ()
    {
        numberOfAnswer=0
        currentQuestion = questionList.getNewQuestion()
        questionTextView.text= currentQuestion!!.questionText
        answerButton1.setImageResource(currentQuestion!!.alternative1)
        answerButton2.setImageResource(currentQuestion!!.alternative2)
        answerButton3.setImageResource(currentQuestion!!.alternative3)
        answerButton4.setImageResource(currentQuestion!!.alternative4)
        answerButton5.setImageResource(currentQuestion!!.alternative5)
        answerButton6.setImageResource(currentQuestion!!.alternative6)
        answerButton7.setImageResource(currentQuestion!!.alternative7)
        answerButton8.setImageResource(currentQuestion!!.alternative8)
        answerButton9.setImageResource(currentQuestion!!.alternative9)

    }
    fun checkAnswer()
    {
        if (numberOfAnswer < currentQuestion?.currectAnswer?.size!!){
            Toast.makeText(this, "You have just ${currentQuestion?.currectAnswer?.size} selection", Toast.LENGTH_LONG).show()

            numberOfAnswer =numberOfAnswer+1


        }
        else
            Toast.makeText(this, "You have just ${currentQuestion?.currectAnswer?.size} selection", Toast.LENGTH_LONG).show()
    }
















}
