package com.example.mobile_development_lab_04

class Question private constructor(val id: Int, val value: String, val result: Boolean) {
    companion object {
        private val questions = listOf(
            Question(0, "Is the capital of France Berlin?", false),
            Question(1, "Is the largest desert in the world the Sahara?", true),
            Question(2, "Is the country of Australia located in North America?", false),
            Question(3, "Is the highest mountain peak in the world Mount Everest?", true),
            Question(4, "Is the city of Tokyo the capital of China?", false),
            Question(5, "Is the country of Brazil located in South America?", true),
            Question(6, "Is the river Nile the longest river in the world?", true),
            Question(7, "Is the city of Sydney the capital of Australia?", false),
            Question(8, "Is the country of Japan an island nation?", true),
            Question(9, "Is the city of Moscow the capital of Poland?", false),
            Question(10, "Brazil?", false)
        )
        fun questionsAmount(): Int {
            return questions.count()
        }

        fun create(id: Int): Question {
            return questions.firstOrNull { it.id == id } ?: questions.last()
        }
    }
}