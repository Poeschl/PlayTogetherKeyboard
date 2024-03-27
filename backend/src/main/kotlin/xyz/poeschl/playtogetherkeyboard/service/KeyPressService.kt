package xyz.poeschl.playtogetherkeyboard.service

import org.springframework.stereotype.Service
import xyz.poeschl.playtogetherkeyboard.models.Key
import xyz.poeschl.playtogetherkeyboard.models.KeyPress
import xyz.poeschl.playtogetherkeyboard.models.KeyStatistics

@Service
class KeyPressService {

  private val currentKeyPresses = mutableMapOf<Key, Int>()

  fun registerKeyPress(key: Key) {
    synchronized(currentKeyPresses) {
      currentKeyPresses[key] = currentKeyPresses.getOrDefault(key, 0) + 1
    }
  }

  fun getCurrentKeyPresses(): KeyStatistics {
    return KeyStatistics(currentKeyPresses.map { KeyPress(it.key, it.value) }.sortedByDescending { it.presses })
  }
}
