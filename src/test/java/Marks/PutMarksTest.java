package Marks;

import bankDataReader.currencyimpl.currencyInterface.PutMarks;
import bankDataReader.db.DataBase;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;

import org.junit.Test;

import java.util.Currency;
import java.util.List;

public class PutMarksTest {
    @Test
    public void putFuncTest(){
        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(0);
            List<String> currencyList = userInfo.getCurrency();

//            currencyList.add()

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
