package com.shuyun.loyalty.query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CardSummary {
    @Id
    @Column(length = 64)
    private String id;
    private Integer initialAmount;
    private Integer remainingAmount;

    public CardSummary() {}

    public CardSummary(String id, Integer initialAmount, Integer remainingAmount) {
        this.id = id;
        this.initialAmount = initialAmount;
        this.remainingAmount = remainingAmount;
    }

    public String getId() {
        return id;
    }

    public Integer getInitialAmount() {
        return initialAmount;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInitialAmount(Integer initialAmount) {
        this.initialAmount = initialAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public CardSummary deductAmount(Integer toBeDeducted) {
        return new CardSummary(id, initialAmount, remainingAmount - toBeDeducted);
    }

    @Override
    public String toString() {
        return "{" + "id='" + id + '\'' + ", initialAmount=" + initialAmount + ", remainingAmount="
                + remainingAmount + '}';
    }
}
