package bankDataReader.currencyimpl.views;

import bankDataReader.currencyimpl.currencyInterface.PutMarks;
import bankDataReader.db.DataBase;
import bankDataReader.db.User;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Currency {

    public static void processCurrencySelection(CallbackQuery callbackQuery, int userId) {
        User userDb = new User();

        String selectedCurrency = callbackQuery.getData();

        List<String> selectedCurrencyList = userDb.getUser(userId).getCurrency();

        selectedCurrencyList.add(selectedCurrency);

        // оновлення списку банків для користувача в базі даних в классі юзер

        userDb.updateUserCurrency(userId, selectedCurrencyList);

    }

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

        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(Math.toIntExact(chat.getId()));

            List<String> userCurrency = userInfo.getCurrency();
            PutMarks<CurrencyEnum> putClass = new PutMarks<>();

            sendMessage.setReplyMarkup(putClass.addButtons(List.of(CurrencyEnum.USD, CurrencyEnum.EUR,
                    CurrencyEnum.PLZ, CurrencyEnum.GBP, CurrencyEnum.CHF,
                    CurrencyEnum.CZK), userCurrency));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
            System.out.println("Something wrong with sending settings message :(");
        }
    }
}
