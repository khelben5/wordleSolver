package com.eduardodev.wordlesolver.module

import com.eduardodev.wordlesolver.config.RANDOM_SEED
import com.eduardodev.wordlesolver.config.WORD_FILE_PATH
import com.eduardodev.wordlesolver.config.WORD_LENGTH
import com.eduardodev.wordlesolver.data.getWordsWithLength
import com.eduardodev.wordlesolver.model.GameState
import com.eduardodev.wordlesolver.model.LetterConstraint.*
import com.eduardodev.wordlesolver.model.Position
import java.io.File
import kotlin.random.Random

fun newGame(wordLength: Int = WORD_LENGTH) = GameState(
    wordLength = wordLength,
    constraints = emptyMap()
)

fun GameState.nextWord(
    file: File = File(WORD_FILE_PATH),
    random: Random = Random(RANDOM_SEED)
) = getWordsWithLength(
    length = wordLength,
    file = file
).filter { word ->
    constraints.all { entry ->
        val letter = entry.key
        val constraint = entry.value
        constraint.isSatisfiedBy(letter, word)
    }
}.random(random)

fun GameState.markLettersAsPresentInWord(vararg letters: Char) = copy(
    constraints = letters.asList().fold(constraints) { acc, letter ->
        acc + (letter to MustContainInWord)
    }
)

fun GameState.markLetterAsPresentAtPosition(
    letter: Char,
    position: Position
): GameState {
    require(position.value <= wordLength) {
        "Position has to be less than word length ($wordLength)!"
    }

    val newIndices: Set<Int>
    val index = position.value - 1
    val constraint = constraints[letter]

    newIndices = if (constraint is MustContainAtIndices) {
        constraint.indices + index
    } else {
        setOf(index)
    }

    return copy(constraints = constraints + (letter to MustContainAtIndices(newIndices)))
}

fun GameState.markLettersAsAbsent(vararg letters: Char) = copy(
    constraints = letters.asList().fold(constraints) { acc, letter ->
        acc + (letter to MustNotContain)
    }
)
