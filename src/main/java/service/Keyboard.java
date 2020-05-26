package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Keyboard {

    public static SendMessage mainKeyboard(long chatId){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();

        //Create note button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Создать")
                .setCallbackData("/create"));

        //Check notes button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Посмотреть")
                .setCallbackData("/check"));

        //Delete notes button
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("Удалить")
                .setCallbackData("/delete"));

        //FQA button
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("FQA")
                .setCallbackData("/fqa"));


        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        list.add(keyboardButtonsRow);
        list.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(list);

        return new SendMessage().setChatId(chatId).setText("Что мы хотим сделать с твоими напоминалками? :3")
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage defaultKeyboard(long chatId, String message){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Отмена")
                .setCallbackData("/menu"));


        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return new SendMessage().setChatId(chatId).setText(message)
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage hourKeyboard(long chatId, String message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();

        keyboardButtonsRow.add(new InlineKeyboardButton().setText("00:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("01:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("02:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("03:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("04:00")
                .setCallbackData("/setHour"));

        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("05:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("06:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("07:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("08:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("09:00")
                .setCallbackData("/setHour"));

        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("10:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("11:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("12:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("13:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("14:00")
                .setCallbackData("/setHour"));

        keyboardButtonsRow4.add(new InlineKeyboardButton().setText("15:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow4.add(new InlineKeyboardButton().setText("16:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow4.add(new InlineKeyboardButton().setText("17:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow4.add(new InlineKeyboardButton().setText("18:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow4.add(new InlineKeyboardButton().setText("19:00")
                .setCallbackData("/setHour"));

        keyboardButtonsRow5.add(new InlineKeyboardButton().setText("20:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow5.add(new InlineKeyboardButton().setText("21:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow5.add(new InlineKeyboardButton().setText("22:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow5.add(new InlineKeyboardButton().setText("23:00")
                .setCallbackData("/setHour"));
        keyboardButtonsRow5.add(new InlineKeyboardButton().setText("Отмена")
                .setCallbackData("/setHour"));

        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        list.add(keyboardButtonsRow);
        list.add(keyboardButtonsRow2);
        list.add(keyboardButtonsRow3);
        list.add(keyboardButtonsRow4);
        list.add(keyboardButtonsRow5);
        inlineKeyboardMarkup.setKeyboard(list);

        return new SendMessage().setChatId(chatId).setText(message)
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage minuteKeyboard(long chatId, String message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();

        keyboardButtonsRow.add(new InlineKeyboardButton().setText("10")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("15")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("20")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("25")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("30")
                .setCallbackData("/setMinute"));

        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("35")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("40")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("45")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("50")
                .setCallbackData("/setMinute"));
        keyboardButtonsRow2.add(new InlineKeyboardButton().setText("55")
                .setCallbackData("/setMinute"));

        keyboardButtonsRow3.add(new InlineKeyboardButton().setText("Отмена")
                .setCallbackData("/create"));

        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        list.add(keyboardButtonsRow);
        list.add(keyboardButtonsRow2);
        list.add(keyboardButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(list);

        return new SendMessage().setChatId(chatId).setText(message)
                .setReplyMarkup(inlineKeyboardMarkup);
    }

    public static SendMessage flagKeyboard(long chatId, String message){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Да, сохраняем")
                .setCallbackData("/saveNote"));

        //Back button
        keyboardButtonsRow.add(new InlineKeyboardButton().setText("Нет, редактируем")
                .setCallbackData("/noteEdit"));

        inlineKeyboardMarkup.setKeyboard(Collections.singletonList(keyboardButtonsRow));

        return new SendMessage().setChatId(chatId).setText(message)
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
