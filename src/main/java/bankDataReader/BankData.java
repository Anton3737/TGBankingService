package bankDataReader;

public class BankData {

    private String name;
    private String priceAtCheckout;
    private String priceForPurchaseByCard;

    public BankData(String name, String priceAtCheckout, String priceForPurchaseByCard) {
        this.name = name;
        this.priceAtCheckout = priceAtCheckout;
        this.priceForPurchaseByCard = priceForPurchaseByCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceAtCheckout() {
        return priceAtCheckout;
    }

    public void setPriceAtCheckout(String priceAtCheckout) {
        this.priceAtCheckout = priceAtCheckout;
    }

    public String getPriceForPurchaseByCard() {
        return priceForPurchaseByCard;
    }

    public void setPriceForPurchaseByCard(String priceForPurchaseByCard) {
        this.priceForPurchaseByCard = priceForPurchaseByCard;
    }

//    @Override
//    public String toString() {
//        return "BankData{" +
//                "name='" + name + '\'' +
//                ", priceAtCheckout='" + priceAtCheckout + '\'' +
//                ", priceForPurchaseByCard='" + priceForPurchaseByCard + '\'' +
//                '}';
//    }
}
