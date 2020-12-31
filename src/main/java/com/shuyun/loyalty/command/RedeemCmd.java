package com.shuyun.loyalty.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RedeemCmd {
    @TargetAggregateIdentifier // (1)
    private final String id;
    private final Integer amount;

    public RedeemCmd(String id, Integer amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }
}
