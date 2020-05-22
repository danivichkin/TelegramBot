import config.Properties;
import dao.impl.UserDaoImpl;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.Keyboard;
import service.UserService;


public class Bot extends TelegramLongPollingBot {

    UserService userService = new UserService();

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {

            //Keyboard
            if (update.getMessage().getText().equals("/start")) {
                long chatId = update.getMessage().getChatId();
                userService.saveUser(chatId, update);

                try {
                    execute(Keyboard.mainKeyboard(chatId));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }


        } else if (update.hasCallbackQuery()) {

            String callBackData = update.getCallbackQuery().getData();
            long chatId = update.getCallbackQuery().getMessage().getChatId();

            switch (callBackData) {

                case "/create":

                    try {
                        execute(Keyboard.defaultKeyboard(chatId));
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }


                    userService.updateLastCommand(chatId, update);
                    break;
                case "/check":
                    userService.updateLastCommand(chatId, update);
                    break;
                case "/delete":
                    userService.updateLastCommand(chatId, update);
                    break;
                case "/cancel":
                    userService.updateLastCommand(chatId, update);

                    try {
                        execute(Keyboard.mainKeyboard(chatId));
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
