package com.springaxon.commandside.order.configuration;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.model.GenericJpaRepository;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.jpa.ContainerManagedEntityManagerProvider;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.messaging.interceptors.BeanValidationInterceptor;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springaxon.commandside.order.aggregate.UserOrder;

/**
 * A configuration for axonframework.
 * Axonframework is used to support eventsourcing and CQRS.
 * 
 * @author idugalic
 *
 */
@Configuration
public class AxonConfiguration {
    
    @Bean
    CommandBus commandBus(TransactionManager transactionManager) {
        SimpleCommandBus commandBus = new SimpleCommandBus(transactionManager, NoOpMessageMonitor.INSTANCE);
        commandBus.registerDispatchInterceptor(new BeanValidationInterceptor());
        return commandBus;
    }
    
    /*
	@Bean
    @Autowired
    public Repository<UserOrder> orderRepository(EntityManagerProvider entityManagerProvider, EventBus eventBus) {
        return new GenericJpaRepository<UserOrder>(entityManagerProvider, UserOrder.class, eventBus);
    }  
    */
    
}

