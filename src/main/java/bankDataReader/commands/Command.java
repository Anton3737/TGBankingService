package bankDataReader.commands;


import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class Command extends BotCommand {

    public Command() {
        super("start", "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage sendMessage = new SendMessage();
        String firstMsg = "Оберіть пункти з меню";
        sendMessage.setText(firstMsg);
        sendMessage.setChatId(chat.getId());

        KeyboardRow row = new KeyboardRow();
        row.add("Отримати інфо");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("Налаштування");

        sendMessage.setReplyMarkup(new ReplyKeyboardMarkup(List.of(row, row2)));

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace(System.out);
            System.out.println("Something wrong :(");
        }

    }
}


