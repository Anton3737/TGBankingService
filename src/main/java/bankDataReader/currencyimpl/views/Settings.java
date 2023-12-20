package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class Settings {

    static void displaySettingsMethod() {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть пункт налаштування";
        sendMessage.setText(titleMessage);
        //sendMessage.setChatId(chat.getId());

        InlineKeyboardButton banks = InlineKeyboardButton.builder().text("Банк").callbackData("Банк").build();
        InlineKeyboardButton currency = InlineKeyboardButton.builder().text("Валюта").callbackData("Валюта").build();
        InlineKeyboardButton betterOffers = InlineKeyboardButton.builder().text("Кращі пропозиції").callbackData("Кращі пропозиції").build();
        InlineKeyboardButton notificationsTime = InlineKeyboardButton.builder().text("Час сповіщень").callbackData("Час сповіщень").build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(banks, currency, betterOffers, notificationsTime))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }


}
