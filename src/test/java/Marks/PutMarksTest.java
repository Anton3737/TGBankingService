package Marks;

import bankDataReader.currencyimpl.currencyInterface.PutMarks;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;

import org.junit.Test;

import java.util.List;

public class PutMarksTest {
    @Test
    public void putFuncTest(){
        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(0);
            int sumbols = userInfo.getSumbols();

            PutMarks<String> sumbolsAftercoma = new PutMarks<>();

            sumbolsAftercoma.addButtons(
                    List.of("2", "3", "4"),
                    List.of(String.valueOf(sumbols))
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
