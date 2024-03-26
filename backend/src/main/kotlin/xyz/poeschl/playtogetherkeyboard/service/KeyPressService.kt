package xyz.poeschl.playtogetherkeyboard.service

import org.springframework.stereotype.Service
import xyz.poeschl.playtogetherkeyboard.models.KeyPress
import xyz.poeschl.playtogetherkeyboard.models.KeyStatistics

@Service
class KeyPressService {

  private val currentKeyPresses = mutableMapOf<Char, Int>()

  fun registerKeyPress(char: Char) {
    synchronized(currentKeyPresses) {
      currentKeyPresses[char] = currentKeyPresses.getOrDefault(char, 0) + 1
    }
  }

  fun getCurrentKeyPresses(): KeyStatistics {
    return KeyStatistics(currentKeyPresses.map { KeyPress(it.key, it.value) }.sortedBy { it.presses })
  }
}
