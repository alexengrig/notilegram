package dev.alexengrig.notigram.service;

import dev.alexengrig.notigram.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;

public interface RegisteredLongPollingBot extends LongPollingBot {
    @Autowired
    default void register(TelegramBotsApi telegramBotsApi) {
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            throw new InternalException("Bot registration exception for: " + getClass(), e);
        }
    }
}
