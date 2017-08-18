package com.springaxon.queryside.blog.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.SequenceNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.springaxon.common.blog.event.BlogPostCreatedEvent;

@ProcessingGroup("default")
@Component
public class BlogPostViewEventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BlogPostViewEventHandler.class);

    //@Autowired
    //private BlogPostRepository blogPostRepository;

    @EventHandler
    public void handle(BlogPostCreatedEvent event, @SequenceNumber Long version) {
        LOG.info("BlogPostCreatedEvent: [{}] ", event.getId());
        //blogPostRepository.save(new BlogPost(event, version));
    }

}
