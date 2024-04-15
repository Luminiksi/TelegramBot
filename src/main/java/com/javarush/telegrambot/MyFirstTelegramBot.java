package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_demo_dz_02_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6612897278:AAERcY_80uX027haiHXQNdIJd-3iJyc0mF8"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        if (getMessageText().equals("/start")) {
            sendTextMessageAsync("Привет!");
        }

        if (getMessageText().equals("/bye")) {
            sendTextMessageAsync("Asta la vista, baby!");
        }

        if (getMessageText().contains("животинка")) {
            sendTextMessageAsync("Ваше любимое животное?",
                    Map.of("Кот", "cat", "Собака", "dog"));
        }

        if (getCallbackQueryButtonKey().equals("cat")) {
            sendPhotoMessageAsync("step_4_pic");
        }

        if (getCallbackQueryButtonKey().equals("dog")) {
            sendPhotoMessageAsync("step_6_pic");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}