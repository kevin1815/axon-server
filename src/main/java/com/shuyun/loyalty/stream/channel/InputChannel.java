package com.shuyun.loyalty.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputChannel {
    @Input(ChannelDefinition.INPUT)
    SubscribableChannel input();
}
