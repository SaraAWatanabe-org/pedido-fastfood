package com.challenge.fastfood.entities;


public class Payment {

    private Long id;
    private String status;
    private String cpf;
    private double priceTotal;
    private Long numberLunch;
    private String transactionId;

    public Payment(Long idLunch) {
        this.numberLunch = idLunch;
        this.status = "PENDING";
    }

    public Payment(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public Long getNumberLunch() {
        return numberLunch;
    }

    public void setNumberLunch(Long numberLunch) {
        this.numberLunch = numberLunch;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
