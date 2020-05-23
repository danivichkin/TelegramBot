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

            //Логика комманд
            switch (lastCommand) {

                case "/create":
                    noteService.createNote(chatId, update);
                    userService.setDefaultCallBack(chatId, update);
                    message.setChatId(chatId).setText("Напоминалка успешно создана\nПродолжим?");

                    try {
                        execute(message);
                        execute(Keyboard.mainKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
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

                    for (int i = 0; i < list.size(); i++) {
                        message.setChatId(chatId).setText(list.get(i).getDescription());
                        try {
                            execute(message);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "/delete":
                    userService.updateLastCommand(chatId, update);
                    break;
                case "/fqa":
                    userService.updateLastCommand(chatId, update);
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
