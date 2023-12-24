package com.example.TGBankingService.Banks;

import bankDataReader.currencyimpl.MinFin;
import bankDataReader.db.DataBase;
import bankDataReader.dto.BankData;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import bankDataReader.enums.CurrencyEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BanksTests {


    @Test
    public void resultDataFOrPrint() {

        try (DataBase db = DataBase.getInstance()) {
            // Testing data

            int userId = 538832410;

            // Use testing data
            UsersDTO userInfo = db.getUser(userId);

            String url = "https://minfin.com.ua/ua/currency/banks/";

            String tmp = "";
            List<String> urlList = new ArrayList<>();
            for (String searchCurrency : userInfo.getCurrency()) {
                tmp = url + searchCurrency.toLowerCase();
                for (BankData searchBank : MinFin.сurrencyParser(tmp)) {
                    if (userInfo.getBank().contains(searchBank.getName())) {
                        System.out.println(searchCurrency);
                        System.out.println(searchBank.getName());
                        System.out.println("Купівля " + searchBank.getPriceToBuy());
                        System.out.println("Продаж " + searchBank.getPriceForSale());
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
