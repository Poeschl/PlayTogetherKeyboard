package xyz.poeschl.playtogetherkeyboard.service

import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import xyz.poeschl.playtogetherkeyboard.models.Key
import xyz.poeschl.playtogetherkeyboard.models.KeyPress
import xyz.poeschl.playtogetherkeyboard.models.KeyStatistics
import kotlin.concurrent.fixedRateTimer
import kotlin.time.Duration.Companion.seconds

@Service
class KeyPressService(
  private val keyMappingService: KeyMappingService,
  private val webSocketService: WebSocketService
) {

  companion object {
    private val LOGGER = LoggerFactory.getLogger(KeyPressService::class.java)
  }

  @Value("#{new Integer('\${PTK_INTERVAL:1}')}")
  private val evaluationInterval = 1

  @Value("#{new Boolean('\${PTK_DRYRUN:false}')}")
  private val dryRun = false

  private val currentKeyPresses = mutableMapOf<Key, Int>()

  fun registerKeyPress(key: Key) {
    if (keyMappingService.isKeyKnown(key)) {
      synchronized(currentKeyPresses) {
        currentKeyPresses[key] = currentKeyPresses.getOrDefault(key, 0) + 1
      }
    }
  }

  fun getCurrentKeyPresses(): KeyStatistics {
    return KeyStatistics(currentKeyPresses.map { KeyPress(it.key, it.value) }.sortedByDescending { it.presses })
  }

  @EventListener(ApplicationReadyEvent::class)
  fun startEvaluationTimer() {
    if (dryRun) {
      LOGGER.info("### DRY RUN ACTIVE ###")
    }
    LOGGER.info("Started evaluation timer with interval {} seconds", evaluationInterval)
    fixedRateTimer(period = evaluationInterval.seconds.inWholeMilliseconds, initialDelay = 0, daemon = true) {
      evaluateKeyPress()
    }
  }

  fun evaluateKeyPress() {
    if (currentKeyPresses.isNotEmpty()) {
      val keyToPress: Key
      synchronized(currentKeyPresses) {
        keyToPress = currentKeyPresses.maxBy { it.value }.key
        currentKeyPresses.clear()
      }
      executeKeyPress(keyToPress)
      webSocketService.sendLastPressedKey(keyToPress)
      webSocketService.sendStatisticsUpdate(getCurrentKeyPresses())
    }
  }

  private fun executeKeyPress(key: Key) {
    if (keyMappingService.isKeyKnown(key)) {
      LOGGER.debug("Will press ${key.code}")

      val keyId = keyMappingService.getKeyId(key)
      if (!dryRun && keyId != null) {
        val pressEvent = NativeKeyEvent(
          NativeKeyEvent.NATIVE_KEY_PRESSED,
          0,
          0,
          keyId,
          NativeKeyEvent.CHAR_UNDEFINED
        )
        val releaseEvent = NativeKeyEvent(
          NativeKeyEvent.NATIVE_KEY_RELEASED,
          0,
          0,
          keyId,
          NativeKeyEvent.CHAR_UNDEFINED
        )
        GlobalScreen.postNativeEvent(pressEvent)
        GlobalScreen.postNativeEvent(releaseEvent)
      }
    }
  }
}
