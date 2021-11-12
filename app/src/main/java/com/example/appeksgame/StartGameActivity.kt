package com.example.appeksgame


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import java.util.logging.LogManager
import android.view.View
import androidx.core.view.postDelayed
import java.util.Timer
import kotlin.properties.Delegates
import java.util.logging.Handler as Handler1
import android.content.Intent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class StartGameActivity : AppCompatActivity(), CoroutineScope {


    private lateinit var job : Job
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    lateinit var db : AppDatabase
    var questionList: QuestionList? = null
    lateinit var scoreTextView: TextView
    lateinit var questionTextView: TextView
    lateinit var answerButton1: ImageButton
    lateinit var answerButton2: ImageButton
    lateinit var answerButton3: ImageButton
    lateinit var answerButton4: ImageButton
    lateinit var answerButton5: ImageButton
    lateinit var answerButton6: ImageButton
    lateinit var answerButton7: ImageButton
    lateinit var answerButton8: ImageButton
    lateinit var answerButton9: ImageButton
    lateinit var nextQuestionButton: Button
    var currentQuestion: Question? = null
    var numberOfAnswer: Int = 1
    var numberOfQuestion: Int = 0
    var score: Int=0


    //


    lateinit var timerTextView: TextView


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        job = Job()
        db = AppDatabase.getInstance(this)

        scoreTextView = findViewById(R.id.scoreTextView)

        questionTextView = findViewById(R.id.questionTextView)
        answerButton1 = findViewById(R.id.answerButton1)
        answerButton2 = findViewById(R.id.answerButton2)
        answerButton3 = findViewById(R.id.answerButton3)
        answerButton4 = findViewById(R.id.answerButton4)
        answerButton5 = findViewById(R.id.answerButton5)
        answerButton6 = findViewById(R.id.answerButton6)
        answerButton7 = findViewById(R.id.answerButton7)
        answerButton8 = findViewById(R.id.answerButton8)
        answerButton9 = findViewById(R.id.answerButton9)
        nextQuestionButton = findViewById(R.id.nextQuestionButton)
        timerTextView = findViewById(R.id.timerTextView)
        loadAllQuestions()



    /*    addNewQuestion(Question(
            1,
            "Which president was killed in the attack?",
            R.drawable.q1_1,
            R.drawable.q1_2,
            R.drawable.q1_3,
            R.drawable.q1_4,
            R.drawable.q1_5,
            R.drawable.q1_6,
            R.drawable.q1_7,
            R.drawable.q1_8,
            R.drawable.q1_9,
            R.drawable.backgrund,
            listOf(
                R.drawable.q1_3
            )

        )
        )
        addNewQuestion(Question(
            2,
            "Who are among the top international men's soccer scorers between 2010 and 2021?",
            R.drawable.fq2_1,
            R.drawable.fq2_2,
            R.drawable.fq2_3,
            R.drawable.fq2_4,
            R.drawable.fq2_5,
            R.drawable.fq2_6,
            R.drawable.fq2_7,
            R.drawable.fq2_8,
            R.drawable.fq2_9,
            R.drawable.backgrund,

            listOf(
                R.drawable.fq2_1, R.drawable.fq2_4
            )

        )

        )
         addNewQuestion(Question(
             3,
             "Who are the winners of the 2021 Oscars?",
             R.drawable.aq3_1,
             R.drawable.aq3_2,
             R.drawable.aq3_3,
             R.drawable.aq3_4,
             R.drawable.aq3_5,
             R.drawable.aq3_6,
             R.drawable.aq3_7,
             R.drawable.aq3_8,
             R.drawable.aq3_9,
             R.drawable.backgrund,

             listOf(
                 R.drawable.aq3_1, R.drawable.aq3_8
             )


         )
                )
        addNewQuestion(Question(
            4,
            "Which monuments are not in Europe?",
            R.drawable.hq4_1,
            R.drawable.hq4_2,
            R.drawable.hq4_3,
            R.drawable.hq4_4,
            R.drawable.hq4_5,
            R.drawable.hq4_6,
            R.drawable.hq4_7,
            R.drawable.hq4_8,
            R.drawable.hq4_9,
            R.drawable.backgrund,

            listOf(
                R.drawable.hq4_7, R.drawable.hq4_8
            )

        )
                )
        addNewQuestion(Question(
            5,
            "Which car companies are not in Europe?",
            R.drawable.cq5_1,
            R.drawable.cq5_2,
            R.drawable.cq5_3,
            R.drawable.cq5_4,
            R.drawable.cq5_5,
            R.drawable.cq5_6,
            R.drawable.cq5_5,
            R.drawable.cq5_8,
            R.drawable.cq5_9,
            R.drawable.backgrund,

            listOf(
                R.drawable.cq5_4, R.drawable.cq5_5,R.drawable.cq5_8,R.drawable.cq5_9
            )

        )
                )

     */




        //loadNewQuestion()


    }

    //användaren kan inte trycka två gånger per alternativ
    class OnDebouncedClickListener(private val delayInMilliSeconds: Long, val action: () -> Unit) :
        View.OnClickListener {
        var enable = true

        override fun onClick(view: View?) {
            if (enable) {
                enable = false
                view?.postDelayed(delayInMilliSeconds) { enable = true }
                action()
            }
        }
    }

    fun View.setOnDebouncedClickListener(delayInMilliSeconds: Long = 32000, action: () -> Unit) {
        val onDebouncedClickListener = OnDebouncedClickListener(delayInMilliSeconds, action)
        setOnClickListener(onDebouncedClickListener)
    }
    fun addNewQuestion(question: Question){
        launch (Dispatchers.IO){
            db.questionDao.insert(question)
        }
    }



    fun loadAllQuestions(){
        val questions= async (Dispatchers.IO){
            db.questionDao.getAll()
        }
        launch {
            val list  = questions.await().toMutableList()


            questionList= QuestionList(list)

            loadNewQuestion()
        }
    }

    fun loadNewQuestion() {
        numberOfQuestion++
        if (numberOfQuestion <= 5) {
            numberOfAnswer = 1

            answerButton1.setOnClickListener(null)
            answerButton2.setOnClickListener(null)
            answerButton3.setOnClickListener(null)
            answerButton4.setOnClickListener(null)
            answerButton5.setOnClickListener(null)
            answerButton6.setOnClickListener(null)
            answerButton7.setOnClickListener(null)
            answerButton8.setOnClickListener(null)
            answerButton9.setOnClickListener(null)




            currentQuestion = questionList?.getNewQuestion()

                    Toast.makeText(this, "testing $numberOfAnswer", Toast.LENGTH_SHORT).show()

            // view text timer(fråga texten visas efter att bilderna göms)

            object : CountDownTimer(9000, 1000) {


                override fun onTick(millisUntilFinished: Long) {
                    questionTextView.text = "Look at the pictures carefully".toString()
                }

                override fun onFinish() {

                    questionTextView.text = currentQuestion!!.questionText
                    answerButton1.apply {
                        setOnDebouncedClickListener {
                            Log.d("check!!!", " dare ziyad mishe $numberOfAnswer")
                            if (checkAnswer(currentQuestion!!.alternative1))
                                answerButton1.setBackgroundResource(currentQuestion!!.alternative1)


                        }
                    }
                    answerButton2.apply {
                        setOnDebouncedClickListener {
                            Log.d("check!!!", " dare ziyad mishe $numberOfAnswer")


                            checkAnswer(currentQuestion!!.alternative2)

                        }
                    }
                    answerButton3.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative3)


                        }
                    }
                    answerButton4.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative4)

                        }
                    }
                    answerButton5.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative5)

                        }
                    }
                    answerButton6.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative6)

                        }
                    }
                    answerButton7.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative7)

                        }
                    }
                    answerButton8.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative8)

                        }
                    }
                    answerButton9.apply {
                        setOnDebouncedClickListener {

                            checkAnswer(currentQuestion!!.alternative9)

                        }
                    }
                }
            }.start()

            //count down timer(tiden som finns kvar till att svara visas)
            object : CountDownTimer(15000, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    timerTextView.setText("seconds remaining to answer: " + millisUntilFinished / 1000)
                }

                override fun onFinish() {

                    nextQuestionButton.setOnClickListener()
                    {
                        loadNewQuestion()
                    }


                    timerTextView.setText("Time is over, Press Next")
                }
            }.start()


            alternativTimer(
                answerButton1,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative1
            )
            alternativTimer(
                answerButton2,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative2
            )
            alternativTimer(
                answerButton3,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative3
            )
            alternativTimer(
                answerButton4,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative4
            )
            alternativTimer(
                answerButton5,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative5
            )
            alternativTimer(
                answerButton6,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative6
            )
            alternativTimer(
                answerButton7,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative7
            )
            alternativTimer(
                answerButton8,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative8
            )
            alternativTimer(
                answerButton9,
                currentQuestion!!.backgrund,
                currentQuestion!!.alternative9
            )

        } else {
            nextQuestionButton.setOnClickListener {
                val intent = Intent(this, ResultRecyckleViewActivity::class.java)
                startActivity(intent)
            }

        }


    }

    //Den här funktionen kommer visa alla alternativ (Imagebuttons)för bara några sekonder
    fun alternativTimer(button: ImageButton, image1: Int, image2: Int) {

        object : CountDownTimer(1000, 500) {


            override fun onTick(millisUntilFinished: Long) {
                button.setBackgroundResource(image1)
            }

            override fun onFinish() {
                object : CountDownTimer(8000, 1000) {

                    override fun onTick(millisUntilFinished: Long) {
                        button.setBackgroundResource(image2)
                    }

                    override fun onFinish() {

                        button.setBackgroundResource(image1)
                    }

                }.start()

            }

        }.start()

    }

    fun checkAnswer(imageID: Int): Boolean {

        if (numberOfAnswer <= currentQuestion?.currectAnswer?.size!!) {
            val restNumberOfSelection = currentQuestion?.currectAnswer?.size!! - numberOfAnswer
            var isFind: Boolean = false

            Log.d("check!!!", "$numberOfAnswer variabeln ändrades")
            numberOfAnswer += 1
            for (option in currentQuestion!!.currectAnswer) {
                if (option == imageID) {
                    score += 1000
                    scoreTextView.text = score.toString()


                    Toast.makeText(
                        applicationContext,
                        "Good job , your score is $score",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("check!!!", " match answer is found")
                    isFind = true
                    return true
                }



            }
            if (isFind == false) {
                Toast.makeText(
                    this,
                    "Not correct! You have $restNumberOfSelection other choices $numberOfAnswer",
                    Toast.LENGTH_SHORT
                ).show()

            }


        } else {
            Toast.makeText(
                this,
                "You have no other choice, plz select Next after questions time $numberOfAnswer",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("check!!!", "tedade javanha kamel shod")


        }

return false
    }


}
