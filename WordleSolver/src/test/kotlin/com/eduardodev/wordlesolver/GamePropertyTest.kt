package com.eduardodev.wordlesolver

import com.eduardodev.wordlesolver.model.GameState
import com.eduardodev.wordlesolver.model.LetterConstraint.*
import com.eduardodev.wordlesolver.module.length
import com.eduardodev.wordlesolver.module.nextWord
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.*
import io.kotest.property.checkAll
import java.io.File
import kotlin.random.Random

class GamePropertyTest : StringSpec({
    "Word length is correct" {
        checkAll(gameGenerator) { game ->
            val word = game.nextWord(
                file = File(WORD_FILE_PATH),
                random = Random(RANDOM_SEED)
            )
            word.length shouldBe WORD_LENGTH
        }
    }
})

val gameGenerator = arbitrary { source ->
    GameState(
        wordLength = WORD_LENGTH,
        constraints = Arb.map(
            keyArb = Arb.char('a'..'z'),
            valueArb = constraintGenerator,
            maxSize = 2
        ).sample(source).value
    )
}

val constraintGenerator = arbitrary { source ->
    when (source.random.nextInt(from = 0, until = 3)) {
        0 -> MustContainAtIndices(
            indices = Arb
                .set(Arb.int(min = 0, max = WORD_LENGTH), range = 1..3)
                .sample(source)
                .value
        )
        1 -> MustContainInWord
        else -> MustNotContain
    }
}

private const val WORD_LENGTH = 5
private const val WORD_FILE_PATH = "src/main/resources/words"
private const val RANDOM_SEED = 4854236547L
