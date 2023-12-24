package databade;

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

            System.out.println(userInfo.getCurrency());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void getUserCurrency() {
        try (DataBase db = DataBase.getInstance()) {
            // Testing data
            String data = CurrencyEnum.USD.toString();
            int userId = 1;

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

            System.out.println(userInfo.getCurrency());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
