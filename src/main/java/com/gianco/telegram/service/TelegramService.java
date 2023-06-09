package com.gianco.telegram.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class TelegramService extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(TelegramService.class);
    @Value("${chat_id}")
    public Long chatId;
    @Value("${chat_id_channel}")
    public Long chatIdChannel;

    String botUser = "giancoSsnBot";

    public TelegramService() {
        super("6264964334:AAG21EzzO1xqzL52f4JBUwYXH_JqkINRcWY");
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage()) {
                execute(createMessage(update.getMessage().getChatId(), update.getMessage().getText()));
            } else if (update.hasChannelPost()) {
                execute(createMessage(update.getChannelPost().getChatId(), update.getChannelPost().getText()));
            }
        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMessageReceived(String text) {
        try {
            execute(createMessage(chatIdChannel, text));
            logger.info(text);
        } catch (TelegramApiException e) {
            logger.error(e.getMessage());
        }
    }

    private SendMessage createMessage(Long chatId, String text) {
        SendMessage msg = new SendMessage();
        msg.setChatId(chatId);
        msg.setText(text);
        return msg;
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }

    @Override
    public String getBotUsername() {
        return botUser;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }
}