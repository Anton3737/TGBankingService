package bankDataReader;

public class BankData {
    private String name;
    private double priceToBuy;
    private double priceForSale;

    public BankData(String name, double priceToBuy, double priceForSale) {
        this.name = name;
        this.priceToBuy = priceToBuy;
        this.priceForSale = priceForSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceToBuy() {
        return priceToBuy;
    }

    public void setPriceToBuy(double priceToBuy) {
        this.priceToBuy = priceToBuy;
    }

    public double getPriceForSale() {
        return priceForSale;
    }

    public void setPriceForSale(double priceForSale) {
        this.priceForSale = priceForSale;
    }
}
