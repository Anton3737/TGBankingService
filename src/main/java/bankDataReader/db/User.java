package bankDataReader.db;

import bankDataReader.enums.Currency;
import bankDataReader.usersDBDTO.UsersDTO;

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
                    List.of("monobank"),
                    List.of(Currency.USD),
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
