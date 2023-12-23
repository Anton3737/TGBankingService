package bankDataReader.currencyimpl.views;

import bankDataReader.enums.CurrencyEnum;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Currency {

    public static void currencyChoice(String currencyName, List<String> currencyList) {
        if (currencyList.contains(currencyName)) {
            currencyList.remove(currencyName);
        } else {
            currencyList.add(currencyName);
        }
    }


    public static void chooseCurrency(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть валюту";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        InlineKeyboardButton USD = InlineKeyboardButton.builder().text("USD").callbackData(CurrencyEnum.USD.toString()).build();
        InlineKeyboardButton EUR = InlineKeyboardButton.builder().text("EUR").callbackData(CurrencyEnum.EUR.toString()).build();
        InlineKeyboardButton PLZ = InlineKeyboardButton.builder().text("PLZ").callbackData(CurrencyEnum.PLZ.toString()).build();
        InlineKeyboardButton GBP = InlineKeyboardButton.builder().text("GBP").callbackData(CurrencyEnum.GBP.toString()).build();
        InlineKeyboardButton CHF = InlineKeyboardButton.builder().text("CHF").callbackData(CurrencyEnum.CHF.toString()).build();
        InlineKeyboardButton CZK = InlineKeyboardButton.builder().text("CZK").callbackData(CurrencyEnum.CZK.toString()).build();

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
