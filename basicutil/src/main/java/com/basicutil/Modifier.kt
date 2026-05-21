package com.basicutil

import androidx.compose.ui.Modifier

inline fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this },
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else {
        then(ifFalse(Modifier))
    }
}