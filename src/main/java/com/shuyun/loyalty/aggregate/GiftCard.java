package com.shuyun.loyalty.aggregate;

import com.shuyun.loyalty.command.IssueCmd;
import com.shuyun.loyalty.command.RedeemCmd;
import com.shuyun.loyalty.event.IssuedEvt;
import com.shuyun.loyalty.event.RedeemedEvt;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class GiftCard {
    @AggregateIdentifier // (1)
    private String id;
    private int remainingValue;

    public GiftCard() {
        // (2)
    }

    @CommandHandler // (3)
    public GiftCard(IssueCmd cmd) {
        if (cmd.getAmount() <= 0)
            throw new IllegalArgumentException("amount <= 0");
        AggregateLifecycle.apply(new IssuedEvt(cmd.getId(), cmd.getAmount())); // (4)
        System.out.println("GiftCard(IssueCmd cmd)");
    }

    @EventSourcingHandler // (5)
    public void on(IssuedEvt evt) {
        id = evt.getId();
        remainingValue = evt.getAmount();
        System.out.println("on(IssuedEvt evt)");
    }

    @CommandHandler
    public GiftCard(RedeemCmd cmd) {
        if (cmd.getAmount() <= 0)
            throw new IllegalArgumentException("amount <= 0");
        System.out.println("========" + cmd.getAmount());
        System.out.println("----------------" + remainingValue);
        if (cmd.getAmount() > remainingValue)
            throw new IllegalStateException("amount > remaining value");
        AggregateLifecycle.apply(new RedeemedEvt(id, cmd.getAmount()));
        System.out.println("handle(RedeemCmd cmd)");
    }

    @EventSourcingHandler
    public void on(RedeemedEvt evt) {
        remainingValue -= evt.getAmount();
        System.out.println("on(RedeemedEvt evt)");
    }
}
