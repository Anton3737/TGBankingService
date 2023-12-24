package bankDataReader.telegram;

import bankDataReader.commands.Command;
import bankDataReader.currencyimpl.views.*;
import bankDataReader.db.DataBase;
import bankDataReader.db.User;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
            try (DataBase db = DataBase.getInstance()) {
                // Testing data
                long userId = callbackQuery.getMessage().getChat().getId();

                // Use testing data
                UsersDTO userInfo = db.getUser((int) userId);
                List<String> banks = userInfo.getBank();

                // Check choice
                if (BanksName.PRIVATBANK.toString().equals(data)) {
                    Banks.oneChoice(BanksName.PRIVATBANK.toString(), banks);

                } else if (BanksName.MONOBANK.toString().equals(data)) {
                    Banks.oneChoice(BanksName.MONOBANK.toString(), banks);

                } else if (BanksName.OSHCHADBANK.toString().equals(data)) {
                    Banks.oneChoice(BanksName.OSHCHADBANK.toString(), banks);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            //
            try (DataBase db = DataBase.getInstance()) {
                // Testing data
                long userId = callbackQuery.getMessage().getChat().getId();

                // Use testing data
                UsersDTO userInfo = db.getUser((int) userId);
                List<String> currencyList = userInfo.getCurrency();

                if (currencyList != null) {
                    if (CurrencyEnum.USD.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.USD.toString(), currencyList);
                    } else if (CurrencyEnum.EUR.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.EUR.toString(), currencyList);
                    } else if (CurrencyEnum.PLZ.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.PLZ.toString(), currencyList);
                    } else if (CurrencyEnum.GBP.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.GBP.toString(), currencyList);
                    } else if (CurrencyEnum.CHF.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.CHF.toString(), currencyList);
                    } else if (CurrencyEnum.CZK.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.CZK.toString(), currencyList);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            // Settings
            if ("Банк".equals(data)) {
                Banks.namesOfBanks(this, callbackQuery.getMessage().getChat());
            } else if ("Валюта".equals(data)) {
                Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
            } else if ("Час сповіщень".equals(data)) {
                NotificationsTime.setNotificationsTime(this, callbackQuery.getMessage().getChat());
            } else if ("Кількість знаків після коми".equals(data)) {
                DecimalPlaces.decimalPlacesMethod(this, callbackQuery.getMessage().getChat());
            }

            // DecimalPlaces
            if ("2".equals(data)||"3".equals(data)||"4".equals(data)) {
                DecimalPlaces.decimalPlacesMethod(this, callbackQuery.getMessage().getChat());
            }

        }
    }

}
