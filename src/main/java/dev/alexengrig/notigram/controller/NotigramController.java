package dev.alexengrig.notigram.controller;

import dev.alexengrig.notigram.service.NotigramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotigramController {
    private final NotigramService notigramService;

    @Autowired
    public NotigramController(NotigramService notigramService) {
        this.notigramService = notigramService;
    }

    @PostMapping("/{chatId}")
    public void send(@PathVariable String chatId, String text) {
        notigramService.send(chatId, text);
    }
}
