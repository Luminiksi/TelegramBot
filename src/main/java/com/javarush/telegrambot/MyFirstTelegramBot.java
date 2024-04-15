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
            sendTextMessageAsync("Пока!");
        }

        if (getMessageText().equals("Как здоровье?")) {
            sendTextMessageAsync("Хорошо");
        }

        if (getMessageText().contains("бомба")) {
            sendTextMessageAsync("Опасность!");
        }

        if (getMessageText().contains("картинка")) {
            sendPhotoMessageAsync("step_8_pic");
        }

        if (getMessageText().contains("кот")) {
            sendTextMessageAsync("Выберите номер кота: ",
                    Map.of("кот 1", "cat1", "кот 2", "cat2"));
                    //Map.of("Текст-на-кнопке1", "команда1", "Текст-на-кнопке2", "команда2"));
        }

        if (getCallbackQueryButtonKey().equals("cat1")) {
            sendPhotoMessageAsync("step_1_pic");
        }

        if (getCallbackQueryButtonKey().equals("cat2")) {
            sendPhotoMessageAsync("step_2_pic");
        }

        if (getMessageText().equals("smile")) {
            var message = getLastSentMessage();
            editTextMessageAsync(message.getMessageId(), message.getText() + " =P ");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}