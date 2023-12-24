package bankDataReader.dto;

import bankDataReader.enums.CurrencyEnum;

import java.util.List;

//Описовий клас для створення екземплярів класу для наших користувачів,
// які будуть додаватись в БД під час підключення до боту
public class UsersDTO {
    List<String> bankList;
    List<String> currencyList;
    int notificationTime;
    int sumbols;

    public UsersDTO(List<String> bankList, List<String> currencyList, int notificationTime, int sumbols) {
        this.bankList = bankList;
        this.currencyList = currencyList;
        this.notificationTime = notificationTime;
        this.sumbols = sumbols;
    }

    public List<String> getBank() {
        return bankList;
    }

    public void setBank(List<String> bank) {
        this.bankList = bank;
    }

    public List<String> getCurrency() {
        return currencyList;
    }

    public void setCurrency(List<String> currency) {
        this.currencyList = currency;
    }

    public int getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(int notificationTime) {
        this.notificationTime = notificationTime;
    }

    public int getSumbols() {
        return sumbols;
    }

    public void setSumbols(int sumbols) {
        this.sumbols = sumbols;
    }
}
