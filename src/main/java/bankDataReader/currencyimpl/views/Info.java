package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Info {

    // Чекаємо BD

    public static void getInfoMethod(AbsSender absSender, Chat chat){
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "INFO test ";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }
    }

}
