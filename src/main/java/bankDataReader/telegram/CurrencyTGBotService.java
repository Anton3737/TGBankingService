package bankDataReader.telegram;

import bankDataReader.commands.Command;
import bankDataReader.currencyimpl.views.*;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

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
                    Banks.namesOfBanks(this, callbackQuery.getMessage().getChat());
                } else if (BanksName.MONOBANK.toString().equals(data)) {
                    Banks.oneChoice(BanksName.MONOBANK.toString(), banks);
                    Banks.namesOfBanks(this, callbackQuery.getMessage().getChat());

                } else if (BanksName.OSHCHADBANK.toString().equals(data)) {
                    Banks.oneChoice(BanksName.OSHCHADBANK.toString(), banks);
                    Banks.namesOfBanks(this, callbackQuery.getMessage().getChat());
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
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
                    } else if (CurrencyEnum.EUR.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.EUR.toString(), currencyList);
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
                    } else if (CurrencyEnum.PLN.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.PLN.toString(), currencyList);
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
                    } else if (CurrencyEnum.GBP.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.GBP.toString(), currencyList);
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
                    } else if (CurrencyEnum.CHF.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.CHF.toString(), currencyList);
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
                    } else if (CurrencyEnum.CZK.toString().equals(data)) {
                        Currency.currencyChoice(CurrencyEnum.CZK.toString(), currencyList);
                        Currency.chooseCurrency(this, callbackQuery.getMessage().getChat());
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
            } else if ("Кращі пропозиції".equals(data)) {
//                BestOffers.bestCurrencyOffers(this, callbackQuery.getMessage().getChat());
            } else if ("Час сповіщень".equals(data)) {
                NotificationsTime.setNotificationsTime(this, callbackQuery.getMessage().getChat());
            } else if ("Кількість знаків після коми".equals(data)) {
                DecimalPlaces.decimalPlacesMethod(this, callbackQuery.getMessage().getChat());
            }


            // DecimalPlaces
            try (DataBase db = DataBase.getInstance()) {

                long userId = callbackQuery.getMessage().getChat().getId();

                UsersDTO userInfo = db.getUser((int) userId);

                if ("2".equals(data) || "3".equals(data) || "4".equals(data)) {
                    userInfo.setSymbols(Integer.parseInt(data));
                    DecimalPlaces.decimalPlacesMethod(this, callbackQuery.getMessage().getChat());
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            // Notification
            try (DataBase db = DataBase.getInstance()) {
                long userId = callbackQuery.getMessage().getChat().getId();

                UsersDTO userInfo = db.getUser((int) userId);

                if ("09:00".equals(data) || "10:00".equals(data) || "11:00".equals(data) || "12:00".equals(data) || "13:00".equals(data) || "14:00".equals(data)
                        || "15:00".equals(data) || "16:00".equals(data) || "17:00".equals(data) || "18:00".equals(data) || "19:00".equals(data)) {

                    userInfo.setNotificationTime(Integer.parseInt(data.replaceAll(":00", "")));

                    NotificationsTime.setNotificationsTime(this, callbackQuery.getMessage().getChat());

                } else if ("Вимкнути час сповіщення".equals(data)) {
                    userInfo.setNotificationTime(-1);
                }

                String tmp = String.valueOf(userInfo.getNotificationTime()) + ":00";
                if (NotificationsTime.timer().equals(tmp)) {
                    Info.getInfoMethod(this, callbackQuery.getMessage().getChat());
                    return;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
