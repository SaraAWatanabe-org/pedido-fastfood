package com.challenge.fastfood.entities;


import java.time.LocalDateTime;
import java.util.List;

public class Lunch {

    private Client client;
    private List<LunchItem> lunchItems;
    private double priceTotal;
    private LunchStatus status;
    private Long id;
    private LocalDateTime date;

    public Lunch(Client client, List<LunchItem> lunchItems, Long id) {
        this.client = client;
        this.lunchItems = lunchItems;
        this.priceTotal = 0;
        for (LunchItem lunchItem : lunchItems) {
            this.priceTotal += lunchItem.getPrice();
        }
        this.status = LunchStatus.RECEBIDO;
        this.id = id;
    }

    public Lunch(){

    }

    public Client getClient() {
        return client;
    }

    public List<LunchItem> getLunchItems() {
            return lunchItems;
        }

    public double getPriceTotal() {
            return priceTotal;
        }


    public void setClient(Client client) {
            this.client = client;
        }

    public void setLunchItems(List<LunchItem> lunchItems) {
            this.lunchItems = lunchItems;
        }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public LunchStatus getStatus() {
        return status;
    }

    public void setStatus(LunchStatus status) {
        this.status = status;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
