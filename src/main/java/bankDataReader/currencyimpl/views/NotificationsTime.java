package bankDataReader.currencyimpl.views;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class NotificationsTime {

   public static void setNotificationsTime(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть час сповіщень";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());
        InlineKeyboardButton nine = InlineKeyboardButton.builder().text("9:00").callbackData("9:00").build();
        InlineKeyboardButton ten = InlineKeyboardButton.builder().text("10:00").callbackData("10:00").build();
        InlineKeyboardButton eleven = InlineKeyboardButton.builder().text("11:00").callbackData("11:00").build();
        InlineKeyboardButton twelve = InlineKeyboardButton.builder().text("12:00").callbackData("12:00").build();
        InlineKeyboardButton eighteen = InlineKeyboardButton.builder().text("18:00").callbackData("18:00").build();
        InlineKeyboardButton turnNotificationsOff = InlineKeyboardButton.builder().text("Вимкнути сповіщення").callbackData("Вимкнути сповіщення").build();


        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(nine, ten, eleven, twelve, eighteen, turnNotificationsOff))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }
    }
}
