package com.eduardodev.wordlesolver.data

import com.eduardodev.wordlesolver.model.Word
import com.eduardodev.wordlesolver.module.length
import java.io.File

fun getWordsWithLength(length: Int, file: File) =
    readWords(file).filter { it.length == length }

private fun readWords(file: File) = file.readLines().map { Word(it) }
