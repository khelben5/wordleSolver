package com.eduardodev.wordlesolver.module

import com.eduardodev.wordlesolver.model.LetterConstraint
import com.eduardodev.wordlesolver.model.LetterConstraint.*
import com.eduardodev.wordlesolver.model.Word

fun LetterConstraint.isSatisfiedBy(letter: Char, word: Word) = when (this) {
    is MustContainAtIndices -> indices.all {
        it < word.length && word[it].uppercase() == letter.uppercase()
    }
    is MustContainInWord -> letter in word
    is MustNotContain -> letter !in word
}
