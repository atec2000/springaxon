package com.springaxon.commandside.order.aggregate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateMember;
import org.axonframework.commandhandling.model.AggregateRoot;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.springaxon.commandside.order.domain.LineItem;
import com.springaxon.common.order.event.OrderCreatedEvent;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

/**
 * A Order aggregate root.
 * 
 */
//@Aggregate
@AggregateRoot
@Entity
public class UserOrder {

    private static final Logger LOG = LoggerFactory.getLogger(UserOrder.class);

    /**
     * Aggregates that are managed by Axon must have a unique identifier. Strategies
     * similar to GUID are often used. The annotation 'AggregateIdentifier' identifies the
     * id field as such.
     */
    @AggregateIdentifier
    private String id;
    
	private String name;

    @AggregateMember
    private Set<LineItem> lineItems;

   /**
     * This default constructor is used by the Repository to construct a prototype
     * UserOrder aggregate. Events are then used to set properties such as the
     * UserOrder aggregate's Id in order to make the Aggregate reflect it's true logical
     * state.
     */
    public UserOrder() {
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
    //@CommandHandler
    public UserOrder(String id, String name, Set<LineItem> lineItems) {
        LOG.debug("Queuing up a new OrderCreatedEvent for order '{}'", id);
        Set<com.springaxon.common.order.model.LineItem> lineItems4Event = new HashSet<com.springaxon.common.order.model.LineItem>();
        for (LineItem li : lineItems) {
        	com.springaxon.common.order.model.LineItem lineItem4Event = new com.springaxon.common.order.model.LineItem();
        	lineItem4Event.setName(li.getName());
        	lineItem4Event.setQuantity(li.getQuantity());
        	lineItem4Event.setUnitPrice(li.getUnitPrice());
        	lineItems4Event.add(lineItem4Event);
        }

        apply(new OrderCreatedEvent(id, name, lineItems4Event));
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
    //@EventSourcingHandler
    @EventHandler    
    public void on(OrderCreatedEvent event) {
        this.id = event.getId();
        this.name = event.getName();
        Set<LineItem> lineItems = new HashSet<LineItem>();
        for (com.springaxon.common.order.model.LineItem li : event.getLineItems()) {
        	LineItem lineItem = new LineItem();
        	lineItem.setName(li.getName());
        	lineItem.setQuantity(li.getQuantity());
        	lineItem.setUnitPrice(li.getUnitPrice());
        	lineItems.add(lineItem);
        }
        this.lineItems = lineItems;        
        LOG.debug("Applied: 'OrderCreatedEvent' [{}]", event.getId());
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
		this.id = id;
	}

    @Column
    public String getName() {
        return name;
    }
    
	public void setName(String name) {
		this.name = name;
	}

    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	public Set<LineItem> getLineItems() {
		return lineItems;
	}  
    
	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}    

}
