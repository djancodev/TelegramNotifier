package com.gianco.telegram.controller;

import com.gianco.telegram.service.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "telegram", produces = MediaType.APPLICATION_JSON_VALUE)
public class TelegramController {
    private TelegramService telegramService;

    public TelegramController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @GetMapping
    public ResponseEntity<Void> sendMessageReceived(@RequestParam String text) {
        telegramService.sendMessageReceived(text);
        return ResponseEntity.ok().build();
    }
}