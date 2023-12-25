package bankDataReader.enums;

public enum CurrencyEnum {
    USD,
    EUR,
    GBP,
    CHF,
    SEK,
    PLN,
    NOK,
    JPY,
    DKK,
    CNY,
    CAD,
    AUD,
    HUF,
    CZK,
    ISL;

    @Override
    public String toString() {
        return switch (this) {
            case USD -> "USD";
            case EUR -> "EUR";
            case GBP -> "GBP";
            case CHF -> "CHF";
            case SEK -> "SEK";
            case PLN -> "PLN";
            case NOK -> "NOK";
            case JPY -> "JPY";
            case DKK -> "DKK";
            case CNY -> "CNY";
            case CAD -> "CAD";
            case AUD -> "AUD";
            case HUF -> "HUF";
            case CZK -> "CZK";
            case ISL -> "ISL";
        };
    }
}
