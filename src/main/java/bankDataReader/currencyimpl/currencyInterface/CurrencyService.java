package bankDataReader.currencyimpl.currencyInterface;

import bankDataReader.enums.Currency;

import java.io.IOException;

public interface CurrencyService {

    double getRate(Currency cc) throws IOException;

}
