package kr.co.userinsight.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // 클라이언트가 웹소켓 연결을 시작할 주소 (Handshake URL)
    registry.addEndpoint("/ws-stomp")
        .setAllowedOriginPatterns("*"); // 모든 도메인에서 접속 허용 (CORS 해결)
  }

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    // 서버가 클라이언트에게 메시지를 보낼 때(Sub) 붙일 접두사
    // 예: /sub/chat/room1 (구독 주소)
    registry.enableSimpleBroker("/sub");

    // 클라이언트가 서버에게 메시지를 보낼 때(Pub) 붙일 접두사
    // 예: /pub/message (메시지 전송 주소)
    registry.setApplicationDestinationPrefixes("/pub");
  }
}