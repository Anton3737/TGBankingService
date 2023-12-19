package bankDataReader.currencyimpl;

import bankDataReader.Currency;

public class GetUrlCurrency {
    public static String getURL(Currency ccy) {
        //в String ccy мають приходити дані від натискання кнопки в розділі "вибір валюти"
        String currency = "";
        switch (ccy) {
            case USD:
                currency = "usd";
                break;
            case EUR:
                currency = "eur";
                break;
            case GBP:
                currency = "gbp";
                break;
            case CHF:
                currency = "chf";
                break;
            case SEK:
                currency = "chf";
                break;
            case PLN:
                currency = "pln";
                break;
            case NOK:
                currency = "nok";
                break;
            case JPY:
                currency = "jpy";
                break;
            case DKK:
                currency = "dkk";
                break;
            case CNY:
                currency = "cny";
                break;
            case CAD:
                currency = "cad";
                break;
            case AUD:
                currency = "aud";
                break;
            case HUF:
                currency = "huf";
                break;
            case CZK:
                currency = "czk";
                break;
            case ISL:
                currency = "ils";
                break;
            //default нам не потрібен, бо дані будемо брати з натискання кнопок
        }
        return "https://minfin.com.ua/ua/currency/banks/" + currency + "/";
    }
}
