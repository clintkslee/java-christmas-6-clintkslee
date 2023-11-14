package christmas.food;

public enum Appetizer {
    양송이수프(6000), 타파스(5500), 시저샐러드(8000);

    private int price;
    Appetizer(int price) {
        this.price = price;
    }

    public String getName() {
        return name();
    }

    public int getPrice() {
        return price;
    }
}
