package xyz.poeschl.playtogetherkeyboard.controller

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import xyz.poeschl.playtogetherkeyboard.models.Key
import xyz.poeschl.playtogetherkeyboard.service.KeyPressService

@Controller
class WebsocketController(private val messageTemplate: SimpMessagingTemplate, private val keyPressService: KeyPressService) {

  @MessageMapping("/keypress")
  fun send(@Payload key: Key) {
    keyPressService.registerKeyPress(key.char)
    messageTemplate.convertAndSend("/topic/stats", keyPressService.getCurrentKeyPresses())
  }
}
