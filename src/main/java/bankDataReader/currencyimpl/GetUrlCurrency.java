package bankDataReader.currencyimpl;

import bankDataReader.enums.CurrencyEnum;

public class GetUrlCurrency {
    /**
     * getUR вхідні дані - CurrencyEnum енам, вихідні - String URL
     * @param ccy
     * @return
     */
    public String getURL(CurrencyEnum ccy) {
        String currency = ccy.name().toLowerCase();

        return "https://minfin.com.ua/ua/currency/banks/" + currency + "/";
    }
}
