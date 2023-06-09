package ezenweb.web.WebSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChattingHandler extends TextWebSocketHandler {

    private static List<WebSocketSession> online = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Establishedsession : " + session);
        online.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("handleTextMessagesession : " + session);
        log.info("handleTextMessagemessage : " + message);
        for (WebSocketSession user : online) {
            user.sendMessage(message);
        }

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Closedsession : " + session);
        log.info("Closedstatus : " + status);
        online.remove(session);
    }
}
