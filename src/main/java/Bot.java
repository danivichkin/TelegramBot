import config.Properties;
import entity.Note;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.Keyboard;
import service.NoteService;
import service.UserService;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    UserService userService = new UserService();
    NoteService noteService = new NoteService();
    SendMessage message = new SendMessage();
    Note note = new Note();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            if (update.getMessage().getText().equals("/start")) {
                long chatId = update.getMessage().getChatId();
                userService.saveUser(chatId, update);
                try {
                    execute(Keyboard.mainKeyboard(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            long chatId = update.getMessage().getChatId();
            String lastCommand = userService.getLastCommand(chatId, update);

            //Логика комманд (диалог)
            switch (lastCommand) {

                case "/create":
                    try {
                        execute(Keyboard.defaultKeyboard(chatId)); //"Создаём новую напоминалку \nВведите название напоминалки:
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    String title = update.getMessage().getText();
                    note.setTitle(title);
                    userService.setCustomCallBack(chatId, update, "/createTitle");
                    break;

                case "/createTitle":
                    message.setChatId(chatId).setText("Теперь описание напоминалки:");
                    try {
                        execute(message);
                        execute(Keyboard.defaultKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    String description = update.getMessage().getText();
                    note.setDescription(description);
                    userService.setCustomCallBack(chatId, update, "/checkNoteBeforeSave"); // /createDescription
                    break;
/*
                case "/createData":
                    message.setChatId(chatId).setText("Когда напомнить?");

                    try {
                        execute(message);
                        execute(Keyboard.defaultKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    Date date = Date.valueOf(update.getMessage().getText());
                    note.setDate(date);

                    message.setChatId(chatId).setText("Напоминалка успешно создана");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;
*/

                case "/checkNoteBeforeSave":
                    try {
                        execute(Keyboard.flagKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setChatId(chatId).setText(note.toString());
                    try {
                        execute(message);
                        execute(Keyboard.flagKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    message.setChatId(chatId).setText("");
                    break;

                case "/saveNote":
                    noteService.addNote(chatId, update, note);
                    message.setChatId(chatId).setText("Успешно");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    noteService.addNote(chatId, update, note);
                    userService.setDefaultCallBack(chatId, update);

                    try {
                        execute(Keyboard.mainKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/delete":
                    title = update.getMessage().getText();
                    try {
                        noteService.deleteNoteByTitle(chatId, update, title);
                        message.setChatId(chatId).setText("Напоминалка успешно удалена");
                    } catch (Exception e) {
                        message.setChatId(chatId).setText("Напоминалка с таким названием не найдена");
                    }

                    try {
                        execute(message);
                        execute(Keyboard.mainKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    userService.setDefaultCallBack(chatId, update);
                    break;
            }

        } else if (update.hasCallbackQuery()) {

            //Обработка комманд !!!клавиатуры!!!
            String callBackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callBackData) {

                case "/menu":
                    userService.updateLastCommand(chatId, update);
                    try {
                        execute(Keyboard.mainKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/create":
                    userService.updateLastCommand(chatId, update);
                    try {
                        execute(Keyboard.defaultKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

                case "/check":
                    userService.updateLastCommand(chatId, update);
                    message.setChatId(chatId).setText("Список твоих напоминалок: ");
                    List<Note> list = noteService.getAllNotes(chatId, update);

                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    //Отсылаем все notes
                    for (Note note : list) {
                        message.setChatId(chatId).setText(note.toString());
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                case "/delete":
                    userService.updateLastCommand(chatId, update);
                    message.setChatId(chatId).setText("Введите название напоминалки которую хотите удалить");

                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                    break;

                case "/fqa":
                    userService.updateLastCommand(chatId, update);
                    message.setChatId(chatId).setText("Отдел в разработке");
                    try {
                        execute(message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    break;

            }
        }
    }

    @Override
    public String getBotUsername() {
        return Properties.getBotName();
    }

    @Override
    public String getBotToken() {
        return Properties.getTOKEN();
    }
}
