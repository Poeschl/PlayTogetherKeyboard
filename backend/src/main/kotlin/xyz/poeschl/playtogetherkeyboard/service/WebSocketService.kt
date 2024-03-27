package xyz.poeschl.playtogetherkeyboard.service

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import xyz.poeschl.playtogetherkeyboard.models.Key
import xyz.poeschl.playtogetherkeyboard.models.KeyStatistics

@Service
class WebSocketService(private val messageTemplate: SimpMessagingTemplate) {

  fun sendStatisticsUpdate(statistics: KeyStatistics) {
    messageTemplate.convertAndSend("/topic/stats", statistics)
  }

  fun sendLastPressedKey(key: Key) {
    messageTemplate.convertAndSend("/topic/lastPressed", key)
  }
}
