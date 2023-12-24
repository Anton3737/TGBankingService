package bankDataReader.telegram;

import bankDataReader.currencyimpl.MinFin;
import bankDataReader.db.DataBase;
import bankDataReader.dto.BankData;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.CurrencyEnum;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TelegramPrettyPrinter {
//    public static final String FORMAT = "%s %s => UAH = %s";
//    String pattern = "#.00";
//    String buy = "Купити";
//    String sale = "Продати";
//
//    public String setDecimalPattern(String number) {
//        switch (number) {
//            case "2" -> pattern = "#.00";
//            case "3" -> pattern = "#.000";
//            case "4" -> pattern = "#.0000";
//        }
//        return pattern;
//    }
//
//    public String prettyRateBuy(double price, CurrencyEnum ccy) {
//        DecimalFormat df = new DecimalFormat(pattern);
//        df.setRoundingMode(RoundingMode.CEILING);
//        return String.format(FORMAT, buy, ccy, df.format(price));
//    }
//
//    public String prettyRateSale(double price, CurrencyEnum ccy) {
//        DecimalFormat df = new DecimalFormat(pattern);
//        df.setRoundingMode(RoundingMode.FLOOR);
//        return String.format(FORMAT, sale, ccy, df.format(price));
//    }

    //Цей метод для тесту чи працюють попередні
//    public static void main(String[] args) {
//        TelegramPrettyPrinter tpp = new TelegramPrettyPrinter();
//        tpp.setDecimalPattern("3");
//        System.out.println(tpp.prettyRateBuy(67.89066, CurrencyEnum.USD) + "\n" + tpp.prettyRateSale(67.89066, CurrencyEnum.USD));
//    }


//    private static String formatNumber(float number, int digits) {
//        // Create sample for DecimalFormat
//
//        DecimalFormat df = new DecimalFormat("#." + "#".repeat(Math.max(0, digits)));
//
//        // Formatting
//        return df.format(number);
//    }

    private static String textPretty(List<BankData> bankList) {
        StringBuilder stringBuilder = new StringBuilder("Інфо:\n");
        for (BankData bank: bankList) {
            stringBuilder.append(bank.getName()).append(" ").append(bank.getCurrencyCode()).append(":\n");
            stringBuilder.append("Продаж: ").append(bank.getPriceForSale()).append("\n");
            stringBuilder.append("Купівля: ").append(bank.getPriceToBuy()).append("\n\n");
        }

        return stringBuilder.toString();
    }

    public static String resultDataForPrint(Chat chat) {

        try (DataBase db = DataBase.getInstance()) {
            // Testing data

            long userId = chat.getId();

            // Use testing data
            UsersDTO userInfo = db.getUser((int) userId);

            List<BankData> dataInfo = new ArrayList<>();
            for (String searchCurrency : userInfo.getCurrency()) {
                for (BankData searchBank : MinFin.сurrencyParser(searchCurrency.toLowerCase())) {
                    if (userInfo.getBank().contains(searchBank.getName())) {
                        System.out.println(searchCurrency);
                        System.out.println(searchBank.getName());
                        System.out.println("Купівля " + searchBank.getPriceToBuy());
                        System.out.println("Продаж " + searchBank.getPriceForSale());

                        dataInfo.add(searchBank);

                    }
                }
            }
            return textPretty(dataInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
