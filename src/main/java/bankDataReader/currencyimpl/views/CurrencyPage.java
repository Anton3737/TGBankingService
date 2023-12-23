package bankDataReader.currencyimpl.views;

import bankDataReader.currencyimpl.currencyInterface.PutMarks;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.Currency;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class CurrencyPage {

    public static void chooseCurrency(AbsSender absSender, Chat chat){
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть валюту";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());


        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(Math.toIntExact(chat.getId()));
            List<Currency> banks = userInfo.getCurrency();

            PutMarks<Currency> сurrency = new PutMarks<>();

            sendMessage.setReplyMarkup(сurrency.addButtons(
                    List.of(Currency.USD, Currency.EUR, Currency.PLN, Currency.GBP,
                            Currency.CHF, Currency.CZK),
                    banks.stream()
                            .map(Object::toString)
                            .toList()
            ));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something wrong with sending settings message :(");
        }
    }
}
