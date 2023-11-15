package christmas.food;

public enum Badge {
    별(5000), 트리(10000), 산타(20000);

    private int price;
    Badge(int price) {
        this.price = price;
    }

    public String getName() {
        return name();
    }

    public int getPrice() {
        return price;
    }
}
