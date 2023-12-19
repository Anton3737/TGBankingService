package bankDataReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MinFin {

    public List CurrencyParser(String url) throws IOException {

        List<BankData> bankDataUSDList = new ArrayList<>();

        Connection connection = Jsoup.connect(url);

        connection.userAgent("Mozilla/5.0");
        Document document = connection.ignoreContentType(true).get();

        Elements postName = document.getElementsByAttributeValue("class", "js-ex-rates mfcur-table-bankname");

        String htmlString = postName.toString();

        String patternString = "data-title=\"([^\"]*)\" data-card=\"([^\"]*)\"><a .*?>(.*?)</a>";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(htmlString);

        while (matcher.find()) {
            String[] bankDataUSDSArray = new String[3];
            if (bankDataUSDSArray.length == 3) {
                // купівля за готівку
                String dataTitle = matcher.group(1);
                //  купівля по картці
                String dataCard = matcher.group(2);
                String name = matcher.group(3).replaceAll("<span[^>]*>.*?</span>", "").toUpperCase().trim();
                BankData bankDataUSDObj = new BankData(name, dataTitle, dataCard);
                bankDataUSDList.add(bankDataUSDObj);
            }
        }

//        while (matcher.find()) {
//            String[] bankDataUSDSArray = new String[5];
//            if (bankDataUSDSArray.length == 3) {
//                String dataTitle = matcher.group(1);
//                String dataCard = matcher.group(2);
//                String name = matcher.group(3).replaceAll("<span[^>]*>.*?</span>", "").toUpperCase().trim();
//                BankData bankDataUSDObj = new BankData(name, dataTitle, dataCard);
//                bankDataUSDList.add(bankDataUSDObj);
//            }
//        }

        System.out.println("розмір колекції " + bankDataUSDList.size());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/java/bankDataReader/BankData.json"), bankDataUSDList);

        return bankDataUSDList;
    }

    public static void main(String[] args) throws IOException {
        MinFin minFin = new MinFin();
        GetUrlCurrency getUrlCurrency = new GetUrlCurrency();
        System.out.println(getUrlCurrency.getURL(Currency.USD));
        minFin.CurrencyParser(getUrlCurrency.getURL(Currency.USD));

        //        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/usd/");
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/eur/");  // Курс євро в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/gbp/");  // Курс англійського фунта стерлінгів в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/chf/");  // Курс швейцарського франка в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/sek/");  // Курс шведської крони в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/pln/");  // Курс польського злотого в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/nok/");  // Курс норвезької крони в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/jpy/");  // Курс японської єни в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/dkk/");  // Курс датської крони в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/cny/");  // Курс юаня женьміньбі в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/cad/");  // Курс канадського долара в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/aud/");  // Курс австралійського долара в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/huf/");  // Курс угорського форінта в банках України
//        minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/czk/");  // Курс чеської крони в банках України
//     minFin.CurrencyParser("https://minfin.com.ua/ua/currency/banks/ils/");  // Курс ізраїльського шекеля в банках України

    }


}
