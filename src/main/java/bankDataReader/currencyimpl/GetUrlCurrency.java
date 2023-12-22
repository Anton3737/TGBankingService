package bankDataReader.currencyimpl;

import bankDataReader.enums.Currency;

public class GetUrlCurrency {
    /**
     * getUR вхідні дані - Currency енам, вихідні - String URL
     * @param ccy
     * @return
     */
    public String getURL(Currency ccy) {
        String currency = ccy.name().toLowerCase();

        return "https://minfin.com.ua/ua/currency/banks/" + currency + "/";
    }
}
