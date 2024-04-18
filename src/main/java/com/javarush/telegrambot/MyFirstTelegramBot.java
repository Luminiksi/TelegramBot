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
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника", "step_1_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT, Map.of("Взять сосиску! + 20 славы", "step_2_btn",
                    "Взять рыбку! + 20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! + 20 славы", "step_2_btn"));
        }

//        2. взломать робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn") && getUserGlory() == 20) {
            addUserGlory(20);

            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота-пылесоса", "step_3_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_3_btn") && getUserGlory() == 40) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT, Map.of("Отправить за едой! + 30 славы", "step_4_btn",
                    "Покататься на пылесосе! + 30 славы", "step_4_btn",
                    "Убежать от пылесоса! + 30 славы", "step_4_btn"));
        }

//        3. взломать камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn") && getUserGlory() == 70) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить его", "step_6_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_6_btn") && getUserGlory() == 100) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Помахать себе в зеркале! + 40 славы", "step_7_btn",
                    "Бегать по комнате и снимать всё! + 40 славы", "step_7_btn",
                    "Сделать колесо с GoPro! + 40 славы", "step_7_btn"));
        }

//        4. взломать комьютер
        if (getCallbackQueryButtonKey().equals("step_7_btn") && getUserGlory() == 140) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взломать пароль от компьютера", "step_8_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_8_btn") && getUserGlory() == 180) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Открыть косынку! + 50 славы", "step_9_btn",
                    "Серфить интернет! + 50 славы", "step_9_btn",
                    "Скачать видео с GoPro! + 50 славы", "step_9_btn"));
        }

//        5. хвастаемся дворовым котам
        if (getCallbackQueryButtonKey().equals("step_9_btn") && getUserGlory() == 230) {
            addUserGlory(50);
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }


    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}