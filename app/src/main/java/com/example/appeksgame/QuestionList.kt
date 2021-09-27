package com.example.appeksgame

import Question

class QuestionList {
    private val questionList= mutableListOf<Question>()
    private val usedQuestion = mutableListOf<Question>()

    init {
        createQuestion()
    }

    private fun createQuestion (){
        val question1 = Question(
            1,
            "Which president died in a attack",
            R.drawable.q1_1,
            R.drawable.q1_2,
            R.drawable.q1_3,
            R.drawable.q1_4,
            R.drawable.q1_5,
            R.drawable.q1_6,
            R.drawable.q1_7,
            R.drawable.q1_8,
            R.drawable.q1_9,
            listOf(
                R.drawable.q1_3
            )

        )
        questionList.add(question1)
        val question2 = Question(
            2,
            "who are in the top list of international men's football goal scorers between 2010 and 2021",
            R.drawable.fq2_1,
            R.drawable.fq2_2,
            R.drawable.fq2_3,
            R.drawable.fq2_4,
            R.drawable.fq2_5,
            R.drawable.fq2_6,
            R.drawable.fq2_7,
            R.drawable.fq2_8,
            R.drawable.fq2_9,
            listOf(
                R.drawable.fq2_1, R.drawable.fq2_4
            )

        )
        questionList.add(question2)

        val question3 = Question(
            3,
            "who are vinner in Oskar 2021?",
            R.drawable.aq3_1,
            R.drawable.aq3_2,
            R.drawable.aq3_3,
            R.drawable.aq3_4,
            R.drawable.aq3_5,
            R.drawable.aq3_6,
            R.drawable.aq3_7,
            R.drawable.aq3_8,
            R.drawable.aq3_9,
            listOf(
                R.drawable.aq3_1, R.drawable.aq3_8
            )

        )
        questionList.add(question3)

        val question4 = Question(
            4,
            "Which monuments are not in Europe",
            R.drawable.hq4_1,
            R.drawable.hq4_2,
            R.drawable.hq4_3,
            R.drawable.hq4_4,
            R.drawable.hq4_5,
            R.drawable.hq4_6,
            R.drawable.hq4_7,
            R.drawable.hq4_8,
            R.drawable.hq4_9,
            listOf(
                R.drawable.hq4_7, R.drawable.hq4_8
            )

        )
        questionList.add(question4)

        val question5= Question(
            5,
            "Which car companies are not in Europe",
            R.drawable.cq5_1,
            R.drawable.cq5_2,
            R.drawable.cq5_3,
            R.drawable.cq5_4,
            R.drawable.cq5_5,
            R.drawable.cq5_6,
            R.drawable.cq5_7,
            R.drawable.cq5_8,
            R.drawable.cq5_9,
            listOf(
                R.drawable.cq5_4, R.drawable.cq5_5,R.drawable.cq5_7,R.drawable.cq5_9
            )

        )
        questionList.add(question5)

    }
    fun getNewQuestion(): Question {
        if (questionList.size==0)
        {
            questionList.addAll(usedQuestion)
            usedQuestion.clear()

        }


        val rnd = (0 until questionList.size).random()
        val newQuestion = questionList.removeAt(rnd)
        usedQuestion.add(newQuestion)
        return newQuestion

    }
}