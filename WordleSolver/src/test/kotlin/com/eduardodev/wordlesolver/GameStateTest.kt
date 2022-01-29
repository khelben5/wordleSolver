package com.eduardodev.wordlesolver

import com.eduardodev.wordlesolver.model.GameState
import com.eduardodev.wordlesolver.model.Word
import com.eduardodev.wordlesolver.module.*
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.random.Random

class GameStateTest {

    @Test
    fun `Creates new game correctly`() {
        newGame(wordLength = 5)
    }

    @Test
    fun `Returns a random word when no constraints`() {
        newTestGame().nextWord() shouldBe Word("coñac")
    }

    @Test
    fun `Concrete word works`() {
        val game = newTestGame()
            .markLetterAsPresentAtPosition('b', newPosition(3))

        val firstWord = game.nextWord()
        val secondWord = game.markLettersAsAbsent('r').nextWord()

        firstWord shouldBe Word("rubia")
        secondWord shouldBe Word("sibil")
    }

    private fun newTestGame() = newGame(wordLength = WORD_LENGTH)

    private fun GameState.nextWord() = nextWord(
        file = File(WORD_FILE_PATH),
        random = Random(RANDOM_SEED)
    )
}

private const val WORD_LENGTH = 5
private const val WORD_FILE_PATH = "src/main/resources/words"
private const val RANDOM_SEED = 4854236547L
