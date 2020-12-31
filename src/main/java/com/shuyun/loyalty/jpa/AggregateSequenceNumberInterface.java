package com.shuyun.loyalty.jpa;

public interface AggregateSequenceNumberInterface {

    String getAggregateIdentifier();

    Long getSequenceNumber();
}
