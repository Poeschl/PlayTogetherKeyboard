package xyz.poeschl.playtogetherkeyboard.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import xyz.poeschl.playtogetherkeyboard.models.Key
import xyz.poeschl.playtogetherkeyboard.service.KeyPressService
import xyz.poeschl.playtogetherkeyboard.service.WebSocketService

@Controller
class WebsocketController(private val keyPressService: KeyPressService, private val webSocketService: WebSocketService) {

  @MessageMapping("/keypress")
  fun retrieveKeyPress(@Payload key: Key) {
    keyPressService.registerKeyPress(key)
    sendUpdate()
  }

  @MessageMapping("/update")
  fun sendUpdate() {
    webSocketService.sendStatisticsUpdate(keyPressService.getCurrentKeyPresses())
  }
}
