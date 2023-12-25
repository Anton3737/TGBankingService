package bankDataReader.currencyParser.views;

import bankDataReader.currencyParser.marks.PutMarks;
import bankDataReader.db.DataBase;
import bankDataReader.db.User;
import bankDataReader.dto.UsersDTO;
import bankDataReader.enums.BanksName;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Banks {

    public static void processBankSelection(CallbackQuery callbackQuery, int userId) {
        User userDb = new User();

        String selectedBank = callbackQuery.getData();

        List<String> selectedBanks = userDb.getUser(userId).getBank();

        selectedBanks.add(selectedBank);

        // оновлення списку банків для користувача в базі даних в классі юзер
        userDb.updateUserBanks(userId, selectedBanks);
    }


    public static void oneChoice(String bankName, List<String> userBanks) {
        if (userBanks.contains(bankName)) {
            userBanks.remove(bankName);
        } else {
            userBanks.add(bankName);
        }
    }


    public static void namesOfBanks(AbsSender absSender, Chat chat) {
        SendMessage sendMessage = new SendMessage();
        String titleMessage = "Оберіть банк";
        sendMessage.setText(titleMessage);
        sendMessage.setChatId(chat.getId());

        try (DataBase db = DataBase.getInstance()){
            UsersDTO userInfo = db.getUser(Math.toIntExact(chat.getId()));

            List<String> banks = userInfo.getBank();
            PutMarks<BanksName> putClass = new PutMarks<>();
            List<BanksName> bankButtons = List.of(BanksName.PRIVATBANK,
                    BanksName.MONOBANK,
                    BanksName.OSHCHADBANK);
            sendMessage.setReplyMarkup(putClass.addButtons(bankButtons, banks, 1));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
            System.out.println("Something wrong with sending settings message :(");
        }
    }

}
