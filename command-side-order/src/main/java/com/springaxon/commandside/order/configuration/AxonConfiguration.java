package com.springaxon.commandside.order.configuration;

import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.spring.eventsourcing.SpringAggregateSnapshotterFactoryBean;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.springaxon.commandside.order.aggregate.UserOrder;
import com.springaxon.commandside.order.handler.UserOrderCommandHandler;

/**
 * A configuration for axonframework. Axonframework is used to support
 * eventsourcing and CQRS.
 * 
 */
@Configuration
public class AxonConfiguration {

	@Autowired
	private EventStore eventStore;

	@Autowired
	private Snapshotter snapshotter;

	//@Autowired
	//private Cache cache;

	@Bean
	@Scope("prototype")
	public UserOrder userOrder() {
		return new UserOrder();
	}
	
    @Bean
    public SpringAggregateSnapshotterFactoryBean springAggregateSnapshotterFactoryBean() {
        return new SpringAggregateSnapshotterFactoryBean();
    }

	@Bean
	public AggregateFactory<UserOrder> userAggregateFactory() {
		SpringPrototypeAggregateFactory<UserOrder> aggregateFactory = new SpringPrototypeAggregateFactory<>();
		aggregateFactory.setPrototypeBeanName("userOrder");

		return aggregateFactory;
	}

	@Bean
	public Repository<UserOrder> userOrderRepository() {
		EventCountSnapshotTriggerDefinition snapshotTriggerDefinition = new EventCountSnapshotTriggerDefinition(
				snapshotter, 50);

		EventSourcingRepository<UserOrder> repository = new EventSourcingRepository<>(
				userAggregateFactory(), eventStore, snapshotTriggerDefinition);

		return repository;
	}
	
	/*
	@Bean
	public Repository<UserOrder> userOrderRepository() {
		EventCountSnapshotTriggerDefinition snapshotTriggerDefinition = new EventCountSnapshotTriggerDefinition(
				snapshotter, 50);

		CachingEventSourcingRepository<UserOrder> repository = new CachingEventSourcingRepository<>(
				userAggregateFactory(), eventStore, cache, snapshotTriggerDefinition);

		return repository;
	}
	*/
	
	@Bean
	@Autowired 
	public Repository<UserOrder> userOrderAggregateRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
		return new GenericJpaRepository<UserOrder>(entityManagerProvider, UserOrder.class, eventBus); 
	}
	
	
    @Bean
    public UserOrderCommandHandler userCommandHandler() {
        UserOrderCommandHandler userOrderCommandHandler = new UserOrderCommandHandler();
        userOrderCommandHandler.setRepository(userOrderRepository());

        return userOrderCommandHandler;
    }	

	/*
	 * @Bean
	 * 
	 * @Autowired public Repository<UserOrderAggregate>
	 * userOrderAggregateRepository(EntityManagerProvider entityManagerProvider,
	 * EventBus eventBus) { return new
	 * GenericJpaRepository<UserOrderAggregate>(entityManagerProvider,
	 * UserOrderAggregate.class, eventBus); }
	 */

}
