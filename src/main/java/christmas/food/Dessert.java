package christmas.food;

public enum Dessert {
    초코케이크(15000), 아이스크림(5000);

    private int price;
    Dessert(int price) {
        this.price = price;
    }

    public String getName() {
        return name();
    }

    public int getPrice() {
        return price;
    }
}
