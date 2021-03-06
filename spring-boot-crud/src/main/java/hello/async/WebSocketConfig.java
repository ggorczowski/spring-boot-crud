package hello.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import hello.lib.Profiles;

@Configuration
@EnableWebSocketMessageBroker
@Profile(Profiles.NOT_TEST)
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
	@Value("${server.context-path}")
	private String appContext;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic", "/queue");
		config.setApplicationDestinationPrefixes(appContext);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/websocket").withSockJS();
	}

}
