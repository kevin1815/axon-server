package com.shuyun.loyalty.stream.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputChannel {
    @Output(ChannelDefinition.OUTPUT)
    MessageChannel output();
}
