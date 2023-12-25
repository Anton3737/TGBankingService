package com.example.TGBankingService;

import bankDataReader.telegram.TelegramBotService;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TgBankingServiceApplication {

    public static void main(String[] args) {

        new TelegramBotService();
    }

}
