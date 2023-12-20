package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class DecimalPlaces {

    static void decimalPlacesMethod() {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть кількість символів після коми";
        sendMessage.setText(titleMessage);
        //sendMessage.setChatId(chat.getId());

        InlineKeyboardButton two = InlineKeyboardButton.builder().text("2").callbackData("2").build();
        InlineKeyboardButton three = InlineKeyboardButton.builder().text("3").callbackData("3").build();
        InlineKeyboardButton four = InlineKeyboardButton.builder().text("4").callbackData("4").build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(two, three, four))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }
}
