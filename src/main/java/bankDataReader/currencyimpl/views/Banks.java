package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class Banks {

    static void namesOfBanks(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть банк";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        InlineKeyboardButton Private = InlineKeyboardButton.builder().text("ПриватБанк").callbackData("ПриватБанк").build();
        InlineKeyboardButton Mono = InlineKeyboardButton.builder().text("Монобанк").callbackData("Монобанк").build();
        InlineKeyboardButton Oshchad = InlineKeyboardButton.builder().text("Ощадбанк").callbackData("Ощадбанк").build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(Private, Mono, Oshchad))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }

    }
}
