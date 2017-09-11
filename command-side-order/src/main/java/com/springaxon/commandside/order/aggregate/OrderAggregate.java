package com.springaxon.commandside.order.aggregate;

import java.util.Date;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springaxon.commandside.order.command.CreateOrderCommand;
import com.springaxon.common.order.event.OrderCreatedEvent;
import com.springaxon.common.order.model.OrderCategory;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * A BlogPost aggregate root.
 * 
 * @author idugalic
 *
 */
@Aggregate
public class OrderAggregate{

    private static final Logger LOG = LoggerFactory.getLogger(OrderAggregate.class);

    /**
     * Aggregates that are managed by Axon must have a unique identifier. Strategies
     * similar to GUID are often used. The annotation 'AggregateIdentifier' identifies the
     * id field as such.
     */
    @AggregateIdentifier
    private String id;
    private String title;
    private String rawContent;
    private String publicSlug;
    private Boolean draft;
    private Boolean broadcast;
    private Date publishAt;
    private OrderCategory category;
    private String authorId;

    /**
     * This default constructor is used by the Repository to construct a prototype
     * BlogPostAggregate. Events are then used to set properties such as the
     * BlogPostAggregate's Id in order to make the Aggregate reflect it's true logical
     * state.
     */
    public OrderAggregate() {
    }

    /**
     * This constructor is marked as a 'CommandHandler' for the AddProductCommand. This
     * command can be used to construct new instances of the Aggregate. If successful a
     * new BlogPostAggregate is 'applied' to the aggregate using the Axon 'apply' method.
     * The apply method appears to also propagate the Event to any other registered 'Event
     * Listeners', who may take further action.
     *
     * @param command
     */
    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        LOG.debug("Command: 'CreateBlogPostCommand' received.");
        LOG.debug("Queuing up a new BlogPostCreatedEvent for blog post '{}'", command.getId());
        apply(new OrderCreatedEvent(command.getId(), command.getTitle(),
                command.getRawContent(), command.getPublicSlug(), command.getDraft(), command.getBroadcast(),
                command.getPublishAt(), command.getCategory(), command.getAuthorId()));
    }

    /**
     * This method is marked as an EventSourcingHandler and is therefore used by the Axon
     * framework to handle events of the specified type (BlogPostCreatedEvent). The
     * BlogPostCreatedEvent can be raised either by the constructor during
     * BlogPostAggregate(CreateBlogPostCommand) or by the Repository when 're-loading' the
     * aggregate.
     *
     * @param event
     */
    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.rawContent = event.getRawContent();
        this.publicSlug = event.getPublicSlug();
        this.draft = event.isDraft();
        this.broadcast = event.isBroadcast();
        this.publishAt = event.getPublishAt();
        this.category = event.getCategory();
        this.authorId = event.getAuthorId();
        LOG.debug("Applied: 'BlogPostCreatedEvent' [{}]", event.getId());
    }

    public static Logger getLog() {
        return LOG;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRawContent() {
        return rawContent;
    }

    public String getPublicSlug() {
        return publicSlug;
    }

    public Boolean getDraft() {
        return draft;
    }

    public Boolean getBroadcast() {
        return broadcast;
    }

    public Date getPublishAt() {
        return publishAt;
    }

    public OrderCategory getCategory() {
        return category;
    }

    public String getAuthorId() {
        return authorId;
    }

}
