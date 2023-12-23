package bankDataReader.telegram;

import bankDataReader.enums.CurrencyEnum;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TelegramPrettyPrinter {
    public static final String FORMAT = "%s %s => UAH = %s";
    String pattern = "#.00";
    String buy = "Купити";
    String sale = "Продати";

    public String setDecimalPattern(String number) {
        switch (number) {
            case "2" -> pattern = "#.00";
            case "3" -> pattern = "#.000";
            case "4" -> pattern = "#.0000";
        }
        return pattern;
    }

    public String prettyRateBuy(double price, CurrencyEnum ccy) {
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(RoundingMode.CEILING);
        return String.format(FORMAT, buy, ccy, df.format(price));
    }

    public String prettyRateSale(double price, CurrencyEnum ccy) {
        DecimalFormat df = new DecimalFormat(pattern);
        df.setRoundingMode(RoundingMode.FLOOR);
        return String.format(FORMAT, sale, ccy, df.format(price));
    }

    //Цей метод для тесту чи працюють попередні
//    public static void main(String[] args) {
//        TelegramPrettyPrinter tpp = new TelegramPrettyPrinter();
//        tpp.setDecimalPattern("3");
//        System.out.println(tpp.prettyRateBuy(67.89066, CurrencyEnum.USD) + "\n" + tpp.prettyRateSale(67.89066, CurrencyEnum.USD));
//    }


}
