package bankDataReader.telegram;

import bankDataReader.currencyimpl.MinFin;
import bankDataReader.db.DataBase;
import bankDataReader.dto.BankData;
import bankDataReader.dto.UsersDTO;
import org.telegram.telegrambots.meta.api.objects.Chat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TelegramPrettyPrinter {

    private static String formatNumber(double number, int digits) {
        DecimalFormat df = new DecimalFormat("#." + "0".repeat(Math.max(digits, digits)));

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

            long userId = chat.getId();

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
            return textPretty(dataInfo, userInfo.getSymbols());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
