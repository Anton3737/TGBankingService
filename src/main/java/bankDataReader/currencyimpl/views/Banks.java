package bankDataReader.currencyimpl.views;

import bankDataReader.db.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import bankDataReader.usersDBDTO.UsersDTO;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.awt.datatransfer.Clipboard;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Banks {

    public void processBankSelection(CallbackQuery callbackQuery, int userId) {
        User userDb = new User();

        String selectedBank = callbackQuery.getData();

        List<String> selectedBanks = userDb.getUser(userId).getBank();

        selectedBanks.add(selectedBank);

        // оновлення списку банків для користувача в базі даних в классі юзер
        userDb.updateUserBanks(userId, selectedBanks);


    }





    public static void namesOfBanks(AbsSender absSender, Chat chat) {
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
