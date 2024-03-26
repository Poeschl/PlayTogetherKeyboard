package xyz.poeschl.playtogetherkeyboard.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.ChannelRegistration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebsocketConfig : WebSocketMessageBrokerConfigurer {

  override fun configureMessageBroker(registry: MessageBrokerRegistry) {
    registry.setApplicationDestinationPrefixes("/app")
    registry.enableSimpleBroker("/topic/")
  }

  override fun registerStompEndpoints(registry: StompEndpointRegistry) {
    registry.addEndpoint("/ws")
  }

  override fun configureClientInboundChannel(registration: ChannelRegistration) {
    super.configureClientInboundChannel(registration)
  }
}
