package databade;

import bankDataReader.currencyParser.views.Currency;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.junit.Test;

import java.util.List;

public class DataBaseTest {


    private void oneChoice(String bankName, List<String> userBanks) {
        if (userBanks.contains(bankName)) {
            userBanks.remove(bankName);
        } else {
            userBanks.add(bankName);
        }
    }

    @Test
    public void getUserTest() {
        try (DataBase db = DataBase.getInstance()) {
            // Testing data
            String data = BanksName.MONOBANK.toString();
            int userId = 0;

            // Use testing data
            UsersDTO userInfo = db.getUser(userId);
            List<String> banks = userInfo.getBank();

            // Check choice
            if (BanksName.PRIVATBANK.toString().equals(data)) {
                oneChoice(BanksName.PRIVATBANK.toString(), banks);

            } else if (BanksName.MONOBANK.toString().equals(data)) {
                oneChoice(BanksName.MONOBANK.toString(), banks);

            } else if (BanksName.OSHCHADBANK.toString().equals(data)) {
                oneChoice(BanksName.OSHCHADBANK.toString(), banks);
            }

            System.out.println(db.getJsonData());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void getUserCurrency() {
        try (DataBase db = DataBase.getInstance()) {
            // Testing data
            String data = CurrencyEnum.CHF.toString();
            int userId = 1;

            // Use testing data
            UsersDTO userInfo = db.getUser(userId);
            List<String> currencyList = userInfo.getCurrency();

            if (currencyList != null) {
                if (CurrencyEnum.USD.toString().equals(data)) {
                    Currency.currencyChoice(CurrencyEnum.USD.toString(), currencyList);
                } else if (CurrencyEnum.EUR.toString().equals(data)) {
                    Currency.currencyChoice(CurrencyEnum.EUR.toString(), currencyList);
                } else if (CurrencyEnum.PLN.toString().equals(data)) {
                    Currency.currencyChoice(CurrencyEnum.PLN.toString(), currencyList);
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

    }



}
