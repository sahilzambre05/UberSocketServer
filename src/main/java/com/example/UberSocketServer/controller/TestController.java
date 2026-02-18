package com.example.UberSocketServer.controller;

import com.example.UberSocketServer.dto.TestRequest;
import com.example.UberSocketServer.dto.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    private final SimpMessagingTemplate simpMessagingTemplate;


    public TestController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse pingCheck(TestRequest message) {
        System.out.println("Received message from client " + message.getData());
        return TestResponse.builder().data("Received").build();
    }


    @Scheduled(fixedDelay = 2000)
    public void sendPeriodicMessage() {
        System.out.println("Executed periodic function");
        simpMessagingTemplate.convertAndSend("/topic/scheduled", "Periodic Message sent " + System.currentTimeMillis());
    }
}
