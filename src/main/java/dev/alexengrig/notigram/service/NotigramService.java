package dev.alexengrig.notigram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotigramService {
    private final NotigramBot bot;

    @Autowired
    public NotigramService(NotigramBot bot) {
        this.bot = bot;
    }

    public void send(String chatId, String text) {
        bot.send(chatId, text);
    }
}
