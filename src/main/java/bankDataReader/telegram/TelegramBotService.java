package bankDataReader.telegram;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotService {

    CurrencyTGBotServide currencyTGBotServide;

    public TelegramBotService() {
        currencyTGBotServide = new CurrencyTGBotServide();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(currencyTGBotServide);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
