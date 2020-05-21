import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.Keyboard;
import service.UserService;


public class Bot extends TelegramLongPollingBot {

    SendMessage message = new SendMessage();

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {

            //Keyboard
            if (update.getMessage().getText().equals("/start")) {
                UserService.addUser(update.getMessage().getChatId() ,update);
                try {
                    execute(Keyboard.mainKeyboard(update.getMessage().getChatId()));
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


                    break;
                case "/check":
                    break;
                case "/delete":
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
