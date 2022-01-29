package com.eduardodev.wordlesolver.module

import com.eduardodev.wordlesolver.model.Position

fun newPosition(value: Int): Position {
    require(value > 0) {
        "Position has to be a positive number greater than zero!"
    }

    return Position(value)
}
