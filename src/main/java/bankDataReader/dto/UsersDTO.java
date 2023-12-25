package bankDataReader.dto;

import java.util.List;
import java.util.Objects;

public class UsersDTO {
    List<String> bankList;
    List<String> currencyList;
    int notificationTime;
    int symbols;

    public UsersDTO(List<String> bankList, List<String> currencyList, int notificationTime, int symbols) {
        this.bankList = bankList;
        this.currencyList = currencyList;
        this.notificationTime = notificationTime;
        this.symbols = symbols;
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

    public int getSymbols() {
        return symbols;
    }

    public void setSymbols(int symbols) {
        this.symbols = symbols;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(notificationTime, symbols);
        result = 31 * result + Objects.hashCode(bankList);
        result = 31 * result + Objects.hashCode(currencyList);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) obj;
        return notificationTime == usersDTO.notificationTime &&
                symbols == usersDTO.symbols &&
                Objects.equals(bankList, usersDTO.bankList) &&
                Objects.equals(currencyList, usersDTO.currencyList);
    }
}
