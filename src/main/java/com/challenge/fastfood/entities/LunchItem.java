package com.challenge.fastfood.entities;


public class LunchItem {

    private Long id;
    private String name;
    private Float price;
    private LunchItemType type;
    private Boolean status;

    public LunchItem(String name, Float price, LunchItemType type, Boolean status) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public LunchItem(Long id, String name, Float price, LunchItemType type, Boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
        this.status = status;
    }

    public LunchItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LunchItemType getType() {
        return type;
    }

    public void setType(LunchItemType type) {
        this.type = type;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}
