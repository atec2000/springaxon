package com.springaxon.commandside.order.eventhandler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springaxon.common.order.event.OrderCreatedEvent;

@ProcessingGroup("default")
@Component
public class UserOrderEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UserOrderEventHandler.class);

    @EventHandler
    public void handle(OrderCreatedEvent event, @SequenceNumber Long version) {
        LOG.info("=-=-=-=-=  received OrderCreatedEvent on command side: [{}] ", event.getId());
    }

}
