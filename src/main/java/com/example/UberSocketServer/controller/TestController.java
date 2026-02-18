package com.example.UberSocketServer.controller;

import com.example.UberSocketServer.dto.TestRequest;
import com.example.UberSocketServer.dto.TestResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

public class TestController {

    @MessageMapping("/ping")
    @SendTo("/topic/ping")
    public TestResponse pingCheck(TestRequest message) {
        System.out.println("Received message from client " + message.getData());
        return TestResponse.builder().data("Received").build();
    }
}
