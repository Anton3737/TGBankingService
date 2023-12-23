package databade;

import bankDataReader.db.DataBase;
import bankDataReader.usersDBDTO.UsersDTO;
import org.junit.Test;

public class DataBaseTest {



    @Test
    public void getUserTest(){
        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(0);
            System.out.println(userInfo.getBank());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
