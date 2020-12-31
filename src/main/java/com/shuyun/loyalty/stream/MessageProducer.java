package com.shuyun.loyalty.stream;

import com.shuyun.loyalty.entity.MemberPointView;
import com.shuyun.loyalty.stream.channel.ChannelDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class MessageProducer implements CommandLineRunner {

    @Autowired
    @Qualifier(ChannelDefinition.OUTPUT)
    private MessageChannel output;

    @Override
    public void run(String... args) throws Exception {
        MemberPointView view = new MemberPointView();
        view.setId(1L);
        view.setMemberId(1001L);
        view.setPoint(880L);
        view.setOverdue(new Date());
        this.output.send(MessageBuilder.withPayload(view).build());
        System.out.println("Send message " + view);
    }
}
