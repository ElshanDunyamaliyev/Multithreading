package dev.elshan.tim_buchalka.sec04.producer_consumer.challenge;

public class Order {
    private Integer id;
    private String shoeType;
    private Integer quantity;

    public Order(Integer id, String shoeType, Integer quantity) {
        this.id = id;
        this.shoeType = shoeType;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShoeType() {
        return shoeType;
    }

    public void setShoeType(String shoeType) {
        this.shoeType = shoeType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
