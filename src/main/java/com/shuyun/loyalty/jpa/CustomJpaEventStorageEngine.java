package com.shuyun.loyalty.jpa;

import org.axonframework.common.AxonConfigurationException;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.DomainEventData;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.SQLErrorCodesResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.function.Predicate;

import static org.axonframework.eventhandling.EventUtils.asDomainEventMessage;

public class CustomJpaEventStorageEngine extends JpaEventStorageEngine {

    public CustomJpaEventStorageEngine(Builder builder) {
        super(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    protected Object createEventEntity(EventMessage<?> eventMessage, Serializer serializer) {
        return new CustomDomainEventEntry(asDomainEventMessage(eventMessage), serializer);
    }

    public static class Builder extends JpaEventStorageEngine.Builder {

        @Override
        public JpaEventStorageEngine.Builder snapshotSerializer(Serializer snapshotSerializer) {
            super.snapshotSerializer(snapshotSerializer);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder upcasterChain(EventUpcaster upcasterChain) {
            super.upcasterChain(upcasterChain);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder persistenceExceptionResolver(
                PersistenceExceptionResolver persistenceExceptionResolver) {
            super.persistenceExceptionResolver(persistenceExceptionResolver);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder eventSerializer(Serializer eventSerializer) {
            super.eventSerializer(eventSerializer);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder snapshotFilter(
                Predicate<? super DomainEventData<?>> snapshotFilter) {
            super.snapshotFilter(snapshotFilter);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder batchSize(int batchSize) {
            super.batchSize(batchSize);
            return this;
        }

        /**
         * Sets the {@link PersistenceExceptionResolver} as a {@link SQLErrorCodesResolver}, using
         * the provided {@link DataSource} to resolve the error codes. <b>Note</b> that the provided
         * DataSource sole purpose in this
         * {@link org.axonframework.eventsourcing.eventstore.EventStorageEngine} implementation is
         * to be used for instantiating the PersistenceExceptionResolver.
         *
         * @param dataSource the {@link DataSource} used to instantiate a
         *        {@link SQLErrorCodesResolver#SQLErrorCodesResolver(DataSource)} as the
         *        {@link PersistenceExceptionResolver}
         *
         * @return the current Builder instance, for fluent interfacing
         * @throws SQLException if creation of the {@link SQLErrorCodesResolver} fails
         */
        public JpaEventStorageEngine.Builder dataSource(DataSource dataSource) throws SQLException {
            super.dataSource(dataSource);
            return this;
        }

        public JpaEventStorageEngine.Builder entityManagerProvider(
                EntityManagerProvider entityManagerProvider) {
            super.entityManagerProvider(entityManagerProvider);
            return this;
        }

        /**
         * Sets the {@link TransactionManager} used to manage transaction around fetching event
         * data. Required by certain databases for reading blob data.
         *
         * @param transactionManager a {@link TransactionManager} used to manage transaction around
         *        fetching event data
         *
         * @return the current Builder instance, for fluent interfacing
         */
        public JpaEventStorageEngine.Builder transactionManager(
                TransactionManager transactionManager) {
            super.transactionManager(transactionManager);
            return this;
        }

        public JpaEventStorageEngine.Builder explicitFlush(boolean explicitFlush) {
            super.explicitFlush(explicitFlush);
            return this;
        }

        public JpaEventStorageEngine.Builder maxGapOffset(int maxGapOffset) {
            super.maxGapOffset(maxGapOffset);
            return this;
        }

        public JpaEventStorageEngine.Builder lowestGlobalSequence(long lowestGlobalSequence) {
            super.lowestGlobalSequence(lowestGlobalSequence);
            return this;
        }

        public JpaEventStorageEngine.Builder gapTimeout(int gapTimeout) {
            super.gapTimeout(gapTimeout);
            return this;
        }

        public JpaEventStorageEngine.Builder gapCleaningThreshold(int gapCleaningThreshold) {
            super.gapCleaningThreshold(gapCleaningThreshold);
            return this;
        }

        /**
         * Initializes a {@link JpaEventStorageEngine} as specified through this Builder.
         *
         * @return a {@link JpaEventStorageEngine} as specified through this Builder
         */
        public CustomJpaEventStorageEngine build() {
            return new CustomJpaEventStorageEngine(this);
        }

        /**
         * Validates whether the fields contained in this Builder are set accordingly.
         *
         * @throws AxonConfigurationException if one field is asserted to be incorrect according to
         *         the Builder's specifications
         */
        @Override
        protected void validate() throws AxonConfigurationException {
            super.validate();
        }
    }
}
