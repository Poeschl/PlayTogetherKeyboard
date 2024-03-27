package xyz.poeschl.playtogetherkeyboard.service

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import org.springframework.stereotype.Service
import xyz.poeschl.playtogetherkeyboard.models.Key

@Service
class KeyMappingService {

  private val keyMap = mapOf<String, Int>(
    "KeyA" to NativeKeyEvent.VC_A,
    "KeyB" to NativeKeyEvent.VC_B,
    "KeyC" to NativeKeyEvent.VC_C,
    "KeyD" to NativeKeyEvent.VC_D,
    "KeyE" to NativeKeyEvent.VC_E,
    "KeyF" to NativeKeyEvent.VC_F,
    "KeyG" to NativeKeyEvent.VC_G,
    "KeyH" to NativeKeyEvent.VC_H,
    "KeyI" to NativeKeyEvent.VC_I,
    "KeyJ" to NativeKeyEvent.VC_J,
    "KeyK" to NativeKeyEvent.VC_K,
    "KeyL" to NativeKeyEvent.VC_L,
    "KeyM" to NativeKeyEvent.VC_M,
    "KeyN" to NativeKeyEvent.VC_N,
    "KeyO" to NativeKeyEvent.VC_O,
    "KeyP" to NativeKeyEvent.VC_P,
    "KeyQ" to NativeKeyEvent.VC_Q,
    "KeyR" to NativeKeyEvent.VC_R,
    "KeyS" to NativeKeyEvent.VC_S,
    "KeyT" to NativeKeyEvent.VC_T,
    "KeyU" to NativeKeyEvent.VC_U,
    "KeyV" to NativeKeyEvent.VC_V,
    "KeyW" to NativeKeyEvent.VC_W,
    "KeyX" to NativeKeyEvent.VC_X,
    "KeyY" to NativeKeyEvent.VC_Y,
    "KeyZ" to NativeKeyEvent.VC_Z,
    "Digit0" to NativeKeyEvent.VC_0,
    "Digit1" to NativeKeyEvent.VC_1,
    "Digit2" to NativeKeyEvent.VC_2,
    "Digit3" to NativeKeyEvent.VC_3,
    "Digit4" to NativeKeyEvent.VC_4,
    "Digit5" to NativeKeyEvent.VC_5,
    "Digit6" to NativeKeyEvent.VC_6,
    "Digit7" to NativeKeyEvent.VC_7,
    "Digit8" to NativeKeyEvent.VC_8,
    "Digit9" to NativeKeyEvent.VC_9,
//    "F1" to NativeKeyEvent.VC_F1,
//    "F2" to NativeKeyEvent.VC_F2,
//    "F3" to NativeKeyEvent.VC_F3,
//    "F4" to NativeKeyEvent.VC_F4,
//    "F5" to NativeKeyEvent.VC_F5,
//    "F6" to NativeKeyEvent.VC_F6,
//    "F7" to NativeKeyEvent.VC_F7,
//    "F8" to NativeKeyEvent.VC_F8,
//    "F9" to NativeKeyEvent.VC_F9,
//    "F10" to NativeKeyEvent.VC_F10,
//    "F11" to NativeKeyEvent.VC_F11,
//    "F12" to NativeKeyEvent.VC_F12,
    "ArrowLeft" to NativeKeyEvent.VC_LEFT,
    "ArrowUp" to NativeKeyEvent.VC_UP,
    "ArrowRight" to NativeKeyEvent.VC_RIGHT,
    "ArrowDown" to NativeKeyEvent.VC_DOWN,
    "Backspace" to NativeKeyEvent.VC_BACKSPACE,
    "Space" to NativeKeyEvent.VC_SPACE,
    "Enter" to NativeKeyEvent.VC_ENTER
  )

  fun isKeyKnown(key: Key): Boolean {
    // Prevent auto-triggered key presses to trigger
    return key.code.isNotBlank() && keyMap.containsKey(key.code)
  }

  fun getKeyId(key: Key): Int? {
    return keyMap[key.code]
  }
}
