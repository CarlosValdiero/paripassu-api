package teste.carlos.teste.paripassu.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import teste.carlos.teste.paripassu.services.WebSocketService;

@Service
public class WebSocketServiceImpl implements WebSocketService {
	
	private final SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	public WebSocketServiceImpl(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	@Override
	public void sendMessage(String destination, Object message) {
		messagingTemplate.convertAndSend(destination, message);
	}

}
