package bankDataReader.telegram;

import bankDataReader.commands.Command;
import bankDataReader.currencyimpl.views.Settings;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class CurrencyTGBotServide extends TelegramLongPollingCommandBot {


    public CurrencyTGBotServide() {

        register(new Command());
    }

    @Override
    public String getBotUsername() {
        return LoginConstants.NAME;
    }

    @Override
    public String getBotToken() {
        return LoginConstants.TOKEN;
    }

//    @Override
//    public void onUpdatesReceived(List<Update> updates) {
//        updates.stream().filter(u -> u.hasMessage()).map(u -> u.getMessage().getText() + " from " + u.getMessage().getChat().getUserName()).peek(System.out::println).collect(Collectors.toList());
//        System.out.println();
//    }


    @Override
    public void processNonCommandUpdate(Update update) {
        
    }

}
