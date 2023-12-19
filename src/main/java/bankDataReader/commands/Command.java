package bankDataReader.commands;


import bankDataReader.currencyimpl.views.Info;
import bankDataReader.currencyimpl.views.Settings;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class Command extends BotCommand {


    public Command(String commandIdentifier, String description) {
        super("start", "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют");
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage sendMessage = new SendMessage();
        // Віконечко 3
        String firstMsg = "Оберіть пункти з меню";
        sendMessage.setText(firstMsg);
        sendMessage.setChatId(chat.getId());

        InlineKeyboardButton info = InlineKeyboardButton.builder().text("Отримати інфо").callbackData("Отримати інфо").build();
        InlineKeyboardButton settings = InlineKeyboardButton.builder().text("Налаштування").callbackData("Налаштування").build();

        InlineKeyboardMarkup inlineKeyboardMarkup = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(info, settings))).build();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        switch (strings.toString()) {
            case "Отримати інфо" -> {

            }

            case "Налаштування" -> {

            }
        }

    }
}
