package com.hexadecimal.example.controller;

import com.hexadecimal.example.websocket.WebSocketServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebSocketController {

    @GetMapping("/page")
    public ModelAndView page() {
        return new ModelAndView("webSocket");
    }

    @RequestMapping("/push/{toUID}")
    public ResponseEntity<String> pushToClient(String message, @PathVariable String toUID) throws Exception {
        WebSocketServer.sendInfo(message, toUID);
        return ResponseEntity.ok("Send Success!");
    }

}