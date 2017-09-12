package com.springaxon.queryside.order.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springaxon.common.order.event.OrderCreatedEvent;

@ProcessingGroup("default")
@Component
public class OrderViewEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(OrderViewEventHandler.class);

    //@Autowired
    //private BlogPostRepository blogPostRepository;

    @EventHandler
    public void handle(OrderCreatedEvent event, @SequenceNumber Long version) {
        LOG.info("=-=-=-=-=  received OrderCreatedEvent: [{}] ", event.getId());
        //blogPostRepository.save(new BlogPost(event, version));
    }

}
