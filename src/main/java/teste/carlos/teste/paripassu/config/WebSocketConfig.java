package teste.carlos.teste.paripassu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websockets")
	       .setAllowedOrigins("http://localhost:4200")
	       .withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config){ 
	    config.enableSimpleBroker("/password");
	    config.setApplicationDestinationPrefixes("/app");
	}
}