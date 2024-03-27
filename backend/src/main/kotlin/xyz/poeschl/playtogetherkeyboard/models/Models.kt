package xyz.poeschl.playtogetherkeyboard.models

data class KeyStatistics(val keys: List<KeyPress>)

data class KeyPress(val key: Key, val presses: Int)

data class Key(val code: String, val key: String)
