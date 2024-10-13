package pe.idat.jms.producer;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class ProducerMessage {

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String nameQueue, String message) {
        Map map = new Gson().fromJson(message, Map.class);
        //String messageText = "Hola " + map.get("nombre");
        System.out.println("Enviando mensaje "+ message + " a la cola " + nameQueue);
        jmsTemplate.convertAndSend("inbound.messageJsonQueue", message);
    }



}
