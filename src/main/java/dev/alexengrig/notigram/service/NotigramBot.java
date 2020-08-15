package dev.alexengrig.notigram.service;

import dev.alexengrig.notigram.configuration.NotigramProperties;
import dev.alexengrig.notigram.exception.InternalException;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class NotigramBot extends TelegramLongPollingBot implements RegisteredLongPollingBot {
    private final String token;
    private final String username;

    public NotigramBot(NotigramProperties notigramProperties) {
        this.token = notigramProperties.getBot().getToken();
        this.username = notigramProperties.getBot().getUsername();
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message;
        if (update.hasMessage()) {
            message = update.getMessage();
        } else if (update.hasChannelPost()) {
            message = update.getChannelPost();
        } else {
            return;
        }
        if (message.hasText()) {
            send(message.getChatId(), "Text: " + message.getText());
        }
    }

    public void send(Long chatId, String text) {
        send(String.valueOf(chatId), text);
    }

    public void send(String chatId, String text) {
        try {
            execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            throw new InternalException("Send message exception for chat: " + chatId + ", with text: " + text, e);
        }
    }
}

