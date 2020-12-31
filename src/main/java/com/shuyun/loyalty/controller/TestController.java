package com.shuyun.loyalty.controller;

import com.shuyun.loyalty.command.IssueCmd;
import com.shuyun.loyalty.command.RedeemCmd;
import com.shuyun.loyalty.query.CardSummary;
import com.shuyun.loyalty.query.FetchCardSummariesQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.LongAccumulator;

@RestController
public class TestController {

    private final CommandGateway commandGateway;

    private final QueryGateway queryGateway;

    private final LongAccumulator accumulator = new LongAccumulator(Long::sum, 112);

    @Autowired
    public TestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @GetMapping("/send")
    public Object send() {
        accumulator.accumulate(1);
        long s1 = accumulator.get();
        accumulator.accumulate(1);
        long s2 = accumulator.get();
        commandGateway.sendAndWait(new IssueCmd("gc" + s1, 100));
        Object v = commandGateway.sendAndWait(new IssueCmd("gc" + s2, 50));
        // commandGateway.sendAndWait(new RedeemCmd("gc" + s1, 10));
        // commandGateway.sendAndWait(new RedeemCmd("gc" + s2, 20));
        return v;
    }

    @GetMapping("/get")
    public void get() {
        try {
            queryGateway
                    .query(new FetchCardSummariesQuery(100, 0),
                            ResponseTypes.multipleInstancesOf(CardSummary.class))
                    .get().forEach(System.out::println);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
