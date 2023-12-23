package databade;

import bankDataReader.db.DataBase;
import bankDataReader.usersDBDTO.UsersDTO;
import org.junit.Test;

import java.util.List;

public class DataBaseTest {

    private void oneChoice(String bankName, List<String> userBanks) {
        if (userBanks.contains(bankName)){
            userBanks.remove(bankName);
        } else {
            userBanks.add(bankName);
        }
    }
    @Test
    public void getUserTest(){
        try (DataBase db = DataBase.getInstance()){
            // Testing data
            String data = "ПриватБанк";
            int userId = 0;

            // Use testing data
            UsersDTO userInfo = db.getUser(userId);
            List<String> banks = userInfo.getBank();

            // Check choice
            if ("ПриватБанк".equals(data)) {
                oneChoice("ПриватБанк", banks);

            } else if ("Монобанк".equals(data)) {
                oneChoice("Монобанк", banks);

            } else if ("Ощадбанк".equals(data)) {
                oneChoice("Ощадбанк", banks);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
