package bankDataReader.currencyimpl.views;

import bankDataReader.currencyimpl.currencyInterface.PutMarks;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Settings {





    public static void displaySettingsMethod(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть пункт налаштування";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        PutMarks<String> marks = new PutMarks<>();

        sendMessage.setReplyMarkup(marks.addButtons(
                List.of("Банк", "Валюта", "Кращі пропозиції", "Час сповіщень"),
                new ArrayList<>()
        ));

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }
    }

}
