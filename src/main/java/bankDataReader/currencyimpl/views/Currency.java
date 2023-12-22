package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class Currency {

    public static void chooseCurrency(AbsSender absSender, Chat chat){
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть валюту";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        InlineKeyboardButton USD = InlineKeyboardButton.builder().text("USD").callbackData("USD").build();
        InlineKeyboardButton EUR = InlineKeyboardButton.builder().text("EUR").callbackData("EUR").build();
        InlineKeyboardButton PLZ = InlineKeyboardButton.builder().text("PLZ").callbackData("PLZ").build();
        InlineKeyboardButton GBP = InlineKeyboardButton.builder().text("GBP").callbackData("GBP").build();
        InlineKeyboardButton CHF = InlineKeyboardButton.builder().text("CHF").callbackData("CHF").build();
        InlineKeyboardButton CZK = InlineKeyboardButton.builder().text("CZK").callbackData("CZK").build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(USD, EUR, PLZ, GBP, CHF, CZK))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }
    }
}
