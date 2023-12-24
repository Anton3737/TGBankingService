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

    private static String formatNumber(double number, int digits) {
        DecimalFormat df = new DecimalFormat("#." + "#".repeat(Math.max(0, digits)));

        return df.format(number);
    }

    private static String textPretty(List<BankData> bankList, int afterComa) {
        StringBuilder stringBuilder = new StringBuilder("Інфо:\n");
        for (BankData bank : bankList) {
            stringBuilder.append(bank.getName()).append(" ").append(bank.getCurrencyCode()).append(":\n");
            stringBuilder.append("Продаж: ").append(formatNumber(bank.getPriceForSale(), afterComa)).append("\n");
            stringBuilder.append("Купівля: ").append(formatNumber(bank.getPriceToBuy(), afterComa)).append("\n\n");
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
            return textPretty(dataInfo, userInfo.getSumbols());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
