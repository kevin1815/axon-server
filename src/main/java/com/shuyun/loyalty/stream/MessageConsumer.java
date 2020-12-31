package com.shuyun.loyalty.stream;

import com.shuyun.loyalty.entity.MemberPointView;
import com.shuyun.loyalty.stream.channel.ChannelDefinition;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

//@Component
public class MessageConsumer {

    @StreamListener(target = ChannelDefinition.INPUT)
    public void input(Message<MemberPointView> message) {
        System.out.println("Received message " + message.getPayload());
    }

}
