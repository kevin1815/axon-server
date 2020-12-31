package com.shuyun.loyalty.jpa;

import org.axonframework.eventhandling.AbstractSequencedDomainEventEntry;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.serialization.Serializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "CustomDomainEventEntry")
@Table(indexes = @Index(columnList = "aggregateIdentifier,sequenceNumber", unique = true))
@EntityListeners(CustomDomainEventEntryListener.class)
public class CustomDomainEventEntry extends AbstractSequencedDomainEventEntry<byte[]> {

    @NotNull
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean sent = false;

    public CustomDomainEventEntry(DomainEventMessage<?> eventMessage, Serializer serializer) {
        super(eventMessage, serializer, byte[].class);
        this.setSent(false);
    }

    /**
     * Default constructor required by JPA
     */
    protected CustomDomainEventEntry() {}

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
