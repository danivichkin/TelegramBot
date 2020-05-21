package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Keyboard {

    public static SendMessage mainKeyboard(long chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        //Create note button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Создать")
                .setCallbackData("/create"));

        //Check notes button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Посмотреть")
                .setCallbackData("/check"));

        //Delete notes button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Удалить")
                .setCallbackData("/delete"));

        //FQA button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("FQA")
                .setCallbackData("/fqa"));


        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return new SendMessage().setChatId(chatId).setText("Что мы хотим сделать с твоими напоминалками? :3").setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage defaultKeyboard(long chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Назад")
                .setCallbackData("/back"));


        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return new SendMessage().setChatId(chatId).setText("Создаём новую напоминалку... \n*Введи текст напоминалки")
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage checkKeyboard(long chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Редактировать")
                .setCallbackData("/edit"));

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Назад")
                .setCallbackData("/back"));


        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return new SendMessage().setChatId(chatId).setText("Создаём новую напоминалку... \n*Введи текст напоминалки")
                .setReplyMarkup(inlineKeyboardMarkup);
    }

}
