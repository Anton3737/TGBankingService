package bankDataReader.db;

import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;

import java.util.List;
import java.util.Map;

public class User {

    public UsersDTO getUser(int userId){
        Map<Integer, UsersDTO> db = DataBase.getInstance().getJsonData();
        UsersDTO user;

        if (db.containsKey(userId)){
            user = db.get(userId);
        } else {
            // Set default settings
            user = new UsersDTO(
                    List.of(BanksName.MONOBANK.toString()),
                    List.of(CurrencyEnum.USD),
                    12,
                    2
            );

            db.put(userId, user);
        }

        return user;
    }

    public void updateUserBanks(int userId, List<String> selectedBanks) {
        try (DataBase database = DataBase.getInstance()) {
            UsersDTO user = database.getUser(userId);

            if (user != null) {
                user.setBank(selectedBanks);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
