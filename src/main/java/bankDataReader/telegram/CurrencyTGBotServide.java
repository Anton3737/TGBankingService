package bankDataReader.telegram;

import bankDataReader.commands.Command;
import bankDataReader.currencyimpl.views.*;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CurrencyTGBotServide extends TelegramLongPollingCommandBot {

    public CurrencyTGBotServide() {

        register(new Command());
    }

    @Override
    public String getBotUsername() {
        return LoginConstants.NAME;
    }

    @Override
    public String getBotToken() {
        return LoginConstants.TOKEN;
    }

//    @Override
//    public void onUpdatesReceived(List<Update> updates) {
//        updates.stream().filter(u -> u.hasMessage()).map(u -> u.getMessage().getText() + " from " + u.getMessage().getChat().getUserName()).peek(System.out::println).collect(Collectors.toList());
//        System.out.println();
//    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            // різні значення data (текст кнопки)

            //
            if ("Отримати інфо".equals(data)) {
                Info.getInfoMethod(this, callbackQuery.getMessage().getChat());
            } else if ("Налаштування".equals(data)) {
                Settings.displaySettingsMethod(this, callbackQuery.getMessage().getChat());
            }


            //  Banks
            if ("ПриватБанк".equals(data)) {

            } else if ("Монобанк".equals(data)) {

            } else if ("Ощадбанк".equals(data)) {

            }



            // Settings
            if ("Банк".equals(data)) {
                Banks.namesOfBanks(this, callbackQuery.getMessage().getChat());
            } else if ("Валюта".equals(data)) {
                CurrencyPage.chooseCurrency(this, callbackQuery.getMessage().getChat());
            } else if ("Кращі пропозиції".equals(data)) {
                BestOffers.bestCurrencyOffers(this, callbackQuery.getMessage().getChat());
            } else if ("Час сповіщень".equals(data)) {
                NotificationsTime.setNotificationsTime(this, callbackQuery.getMessage().getChat());
            }

            // DecimalPlaces
            if ("2".equals(data)) {

            } else if ("3".equals(data)) {

            } else if ("4".equals(data)) {

            }


        }
    }
    
}
