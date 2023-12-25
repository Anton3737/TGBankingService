package bankDataReader.telegram;

import bankDataReader.commands.Command;
import bankDataReader.currencyParser.views.*;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.apache.commons.lang3.EnumUtils;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

public class CurrencyTGBotService extends TelegramLongPollingCommandBot {

    public CurrencyTGBotService() {

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

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            handleInfoAndSettings(messageText, update.getMessage().getChat());
            return;
        }

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Chat chat = callbackQuery.getMessage().getChat();
            String data = callbackQuery.getData();

            handleInfoAndSettings(data, chat);
            handleSettingsOptions(data, chat);
            handleDecimalPlaces(data, chat);
            handleNotification(data, chat);
            handleCurrency(data, chat);
            handleBanks(data, chat);

        }
    }

    private void handleInfoAndSettings(String data, Chat chat) {
        if ("Отримати інфо".equals(data)) {
            Info.getInfoMethod(this, chat);
        } else if ("Налаштування".equals(data)) {
            Settings.displaySettingsMethod(this, chat);
        }
    }

    private void handleBanks(String data, Chat chat) {
        try (DataBase db = DataBase.getInstance()) {

            long userId = chat.getId();

            UsersDTO userInfo = db.getUser((int) userId);
            List<String> banks = userInfo.getBank();

            if (BanksName.PRIVATBANK.toString().equals(data)) {
                Banks.oneChoice(BanksName.PRIVATBANK.toString(), banks);
                Banks.namesOfBanks(this, chat);
            } else if (BanksName.MONOBANK.toString().equals(data)) {
                Banks.oneChoice(BanksName.MONOBANK.toString(), banks);
                Banks.namesOfBanks(this, chat);

            } else if (BanksName.OSHCHADBANK.toString().equals(data)) {
                Banks.oneChoice(BanksName.OSHCHADBANK.toString(), banks);
                Banks.namesOfBanks(this, chat);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCurrency(String data, Chat chat) {
        List<String> currencyList = getUserCurrencyList(chat);

        if (currencyList != null && EnumUtils.isValidEnum(CurrencyEnum.class, data)) {
            Currency.currencyChoice(data, currencyList);
            Currency.chooseCurrency(this, chat);
        }
    }

    private List<String> getUserCurrencyList(Chat chat) {
        try (DataBase db = DataBase.getInstance()) {
            long userId = chat.getId();
            UsersDTO userInfo = db.getUser((int) userId);
            return userInfo.getCurrency();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void handleSettingsOptions(String data, Chat chat) {
        if ("Банк".equals(data)) {
            Banks.namesOfBanks(this, chat);
        } else if ("Валюта".equals(data)) {
            Currency.chooseCurrency(this, chat);
        } else if ("Кращі пропозиції".equals(data)) {
            // BestOffers.bestCurrencyOffers(this, callbackQuery.getMessage().getChat());
        } else if ("Час сповіщень".equals(data)) {
            NotificationsTime.setNotificationsTime(this, chat);
        } else if ("Кількість знаків після коми".equals(data)) {
            DecimalPlaces.decimalPlacesMethod(this, chat);
        }
    }

    private void handleDecimalPlaces(String data, Chat chat) {
        if (Arrays.asList("2", "3", "4").contains(data)) {
            try (DataBase db = DataBase.getInstance()) {
                long userId = chat.getId();
                UsersDTO userInfo = db.getUser((int) userId);
                userInfo.setSymbols(Integer.parseInt(data));
                DecimalPlaces.decimalPlacesMethod(this, chat);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void handleNotification(String data, Chat chat) {
        try (DataBase db = DataBase.getInstance()) {
            long userId = chat.getId();
            UsersDTO userInfo = db.getUser((int) userId);

            if (Arrays.asList("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00").contains(data)) {
                userInfo.setNotificationTime(Integer.parseInt(data.replaceAll(":00", "")));
                NotificationsTime.setNotificationsTime(this, chat);
            } else if ("Вимкнути час сповіщення".equals(data)) {
                userInfo.setNotificationTime(-1);
                NotificationsTime.setNotificationsTime(this, chat);
            }

            String tmp = userInfo.getNotificationTime() + ":00";
            if (NotificationsTime.timer().equals(tmp)) {
                Info.getInfoMethod(this, chat);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}