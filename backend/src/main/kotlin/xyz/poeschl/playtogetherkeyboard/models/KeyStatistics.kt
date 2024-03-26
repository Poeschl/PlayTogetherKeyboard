package xyz.poeschl.playtogetherkeyboard.models

data class KeyStatistics(val keys: List<KeyPress>)

data class KeyPress(val char: Char, val presses: Int)

data class Key(val char: Char)
