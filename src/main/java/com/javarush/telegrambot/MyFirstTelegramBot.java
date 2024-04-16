package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_demo_dz_02_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6612897278:AAERcY_80uX027haiHXQNdIJd-3iJyc0mF8"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
//        1. отобразить сообщение о начале игры -нужно взломать холодильник
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! + 20 славы", "step_2_btn",
                    "Взять рыбку! + 20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! + 20 славы", "step_2_btn"));
        }

//        2. взломать робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота-пылесоса", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить за едой! + 30 славы", "step_4_btn",
                    "Покататься на пылесосе! + 30 славы", "step_4_btn",
                    "Убежать от пылесоса! + 30 славы", "step_4_btn"));
        }

//        3. взломать камеру Go-Pro

//        4. взломать комьютер

//        5. хвастаемся дворовым котам
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}