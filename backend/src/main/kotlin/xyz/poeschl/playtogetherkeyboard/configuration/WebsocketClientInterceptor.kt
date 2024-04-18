package xyz.poeschl.playtogetherkeyboard.configuration

import org.slf4j.LoggerFactory
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.stereotype.Component

/**
 * Tracks connected clients
 */
@Component
class WebsocketClientInterceptor : ChannelInterceptor {

  companion object {
    private const val CLIENT_ID_HEADER = "clientId"
    private val LOGGER = LoggerFactory.getLogger(WebsocketClientInterceptor::class.java)
  }

  private val connectedClients = mutableListOf<String>()

  fun getConnectedClients(): List<String> {
    return connectedClients
  }

  override fun preSend(message: Message<*>, channel: MessageChannel): Message<*> {
    val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)
    if (accessor != null && StompCommand.CONNECT == accessor.command) {
      val clientId = accessor.getFirstNativeHeader(CLIENT_ID_HEADER)
      if (clientId != null) {
        LOGGER.debug("Register client {}", clientId)
        connectedClients.add(clientId)
        accessor.sessionAttributes?.put(CLIENT_ID_HEADER, clientId)
      }
    }
    return message
  }

  override fun postSend(message: Message<*>, channel: MessageChannel, sent: Boolean) {
    val accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor::class.java)
    if (accessor != null && StompCommand.DISCONNECT == accessor.command) {
      val clientId = accessor.sessionAttributes?.get(CLIENT_ID_HEADER)
      LOGGER.debug("Unregister client {}", clientId)
      connectedClients.remove(clientId)
    }
  }
}
