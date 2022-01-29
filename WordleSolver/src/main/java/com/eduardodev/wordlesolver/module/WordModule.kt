package com.eduardodev.wordlesolver.module

import com.eduardodev.wordlesolver.model.Word

val Word.length get() = value.trim().length

operator fun Word.get(index: Int) = value[index]

infix operator fun Word.contains(letter: Char) =
    letter.uppercase() in value.uppercase()
