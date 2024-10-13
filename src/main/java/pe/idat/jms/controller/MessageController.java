package pe.idat.jms.controller;

import org.springframework.web.bind.annotation.RestController;

import pe.idat.jms.producer.ProducerMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MessageController {

    @Autowired
    ProducerMessage producerMessage;

    @PostMapping("/send-to")
    public ResponseEntity<String> sendTo(@RequestBody String message) {
        producerMessage.sendMessage("inbound.messageJsonQueue", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    

}
