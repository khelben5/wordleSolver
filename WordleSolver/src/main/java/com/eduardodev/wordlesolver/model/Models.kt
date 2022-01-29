package com.eduardodev.wordlesolver.model

data class Position(val value: Int)

data class Word(val value: String)

data class GameState(
    val wordLength: Int,
    val constraints: Map<Char, LetterConstraint>
)

sealed class LetterConstraint {
    data class MustContainAtIndices(val indices: Set<Int>) : LetterConstraint()
    object MustContainInWord : LetterConstraint()
    object MustNotContain : LetterConstraint()
}
