package bankDataReader.currencyimpl.currencyInterface;

import bankDataReader.enums.CurrencyEnum;

import java.io.IOException;

public interface CurrencyService {

    double getRate(CurrencyEnum cc) throws IOException;

}
