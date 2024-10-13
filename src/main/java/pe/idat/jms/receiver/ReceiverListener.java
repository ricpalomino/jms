package pe.idat.jms.receiver;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;

@Component
public class ReceiverListener {

    @JmsListener(destination = "inbound.messageJsonQueue")
    @SendTo("outbound.messageQueue")
    public String receiveMessage(final Message json) throws JMSException {
        String messageData = null;
        String response = null;
        System.out.println("MENSAJE RECIBIDO: " + json);
        if(json instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) json;
            messageData = textMessage.getText();
            System.out.println("messageData=" + messageData);
            Map map = new Gson().fromJson(messageData, Map.class);
            response = "Hola " + map.get("nombre");
        }
        return response;
    }

}
