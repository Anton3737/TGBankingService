package bankDataReader.currencyimpl.views;

import bankDataReader.db.User;
import bankDataReader.dto.BankData;
import bankDataReader.enums.BanksName;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Banks {

    public static void processBankSelection(CallbackQuery callbackQuery, int userId) {
        User userDb = new User();

        String selectedBank = callbackQuery.getData();

        List<String> selectedBanks = userDb.getUser(userId).getBank();

        selectedBanks.add(selectedBank);

        // оновлення списку банків для користувача в базі даних в классі юзер
        userDb.updateUserBanks(userId, selectedBanks);

    }


    public static void oneChoice(String bankName, List<String> userBanks) {
        if (userBanks.contains(bankName)) {
            userBanks.remove(bankName);
        } else {
            userBanks.add(bankName);
        }
    }


    public static void namesOfBanks(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть банк";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        InlineKeyboardButton Private = InlineKeyboardButton.builder().text("Приват").callbackData(BanksName.PRIVATBANK.toString()).build();
        InlineKeyboardButton Mono = InlineKeyboardButton.builder().text("Монобанк").callbackData(BanksName.MONOBANK.toString()).build();
        InlineKeyboardButton Oshchad = InlineKeyboardButton.builder().text("Ощадбанк").callbackData(BanksName.OSHCHADBANK.toString()).build();

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
