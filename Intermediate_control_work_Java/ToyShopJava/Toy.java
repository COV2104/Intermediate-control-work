package ToyShopJava;

import java.util.concurrent.atomic.AtomicInteger;

public class Toy {
    transient private static AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(String name, int quantity, double weight) {
        this.id = idCounter.addAndGet(1);
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
