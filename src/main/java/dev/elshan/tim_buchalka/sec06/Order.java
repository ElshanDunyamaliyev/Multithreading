package dev.elshan.tim_buchalka.sec06;

public class Order {
    private Integer id;
    private String carType;
    private Integer quantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order(Integer id, String carType, Integer quantity) {
        this.id = id;
        this.carType = carType;
        this.quantity = quantity;
    }
}
