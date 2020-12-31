package com.shuyun.loyalty.config;

import com.shuyun.loyalty.aggregate.GiftCard;
import com.shuyun.loyalty.jpa.CustomEmbeddedEventStore;
import com.shuyun.loyalty.jpa.CustomEventSourcingRepository;
import com.shuyun.loyalty.jpa.CustomJpaEventStorageEngine;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
@RegisterDefaultEntities(packages = {"org.axonframework.eventsourcing.eventstore.jpa"})
public class AxonContinueConfiguration {

    @Bean
    public CustomEmbeddedEventStore eventStore(EventStorageEngine storageEngine,
            AxonConfiguration configuration) {
        return CustomEmbeddedEventStore.builder().storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    @Bean
    public CustomEventSourcingRepository<GiftCard> giftCardAggregateRepository(
            CustomEmbeddedEventStore eventStore,
            SnapshotTriggerDefinition snapshotTriggerDefinition,
            ParameterResolverFactory parameterResolverFactory) {
        return CustomEventSourcingRepository.builder(GiftCard.class).eventStore(eventStore)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .parameterResolverFactory(parameterResolverFactory).build();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

    @Bean
    public AggregateSnapshotter snapShotter(CustomEmbeddedEventStore eventStore,
            ParameterResolverFactory parameterResolverFactory) {
        return AggregateSnapshotter.builder().eventStore(eventStore)
                .parameterResolverFactory(parameterResolverFactory)
                .aggregateFactories(
                        Collections.singletonList(new GenericAggregateFactory<>(GiftCard.class)))
                .build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(Serializer defaultSerializer,
            PersistenceExceptionResolver persistenceExceptionResolver,
            @Qualifier("eventSerializer") Serializer eventSerializer,
            EntityManagerProvider entityManagerProvider, EventUpcaster contractUpCaster,
            TransactionManager transactionManager) {
        return CustomJpaEventStorageEngine.builder().snapshotSerializer(defaultSerializer)
                .upcasterChain(contractUpCaster)
                .persistenceExceptionResolver(persistenceExceptionResolver)
                .eventSerializer(eventSerializer).entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager).build();
    }

    @Bean
    public EventProcessingConfigurer eventProcessingConfigurer(
            EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.usingSubscribingEventProcessors();
        return eventProcessingConfigurer;
    }

    @Bean
    public EventUpcaster contractUpCaster() {
        return new SingleEventUpcaster() {
            @Override
            protected boolean canUpcast(
                    IntermediateEventRepresentation intermediateRepresentation) {
                return false;
            }

            @Override
            protected IntermediateEventRepresentation doUpcast(
                    IntermediateEventRepresentation intermediateRepresentation) {
                return null;
            }
        };
    }

}
