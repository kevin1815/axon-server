package com.shuyun.loyalty;

import com.shuyun.loyalty.aggregate.GiftCard;
import com.shuyun.loyalty.entity.MemberPointView;
import com.shuyun.loyalty.query.CardSummaryProjection;
import org.axonframework.config.AggregateConfigurer;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

// @EnableBinding({InputChannel.class, OutputChannel.class})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Consumer<MemberPointView> viewConsumer() {
        return view -> {
            System.out.println("Received: " + view);
        };
    }

    public Configuration configuration() {

        // CardSummaryProjection projection = new CardSummaryProjection();
        // EventHandlingConfiguration eventHandlingConfiguration = new EventHandlingConfiguration();
        // eventHandlingConfiguration.registerEventHandler(c -> projection);
        //
        // Configuration configuration = DefaultConfigurer.defaultConfiguration()
        // .configureAggregate(GiftCard.class) // (1)
        // .configureEventStore(c -> new EmbeddedEventStore(new InMemoryEventStorageEngine())) //
        // (2)
        // .registerModule(eventHandlingConfiguration) // (3)
        // .registerQueryHandler(c -> projection) // (4)
        // .buildConfiguration(); // (5)
        // configuration.start();
        return null;
    }

}
