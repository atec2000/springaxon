package com.springaxon.common.order.event;

import java.util.Set;

import com.springaxon.common.order.model.LineItem;
import com.springaxon.common.event.AbstractEvent;

public class OrderCreatedEvent extends AbstractEvent {

	private static final long serialVersionUID = -5017075322968712692L;
	
	private String name;

	private Set<LineItem> lineItems;

    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String id, String name, Set<LineItem> lineItems) {
        super(id);
        this.name = name;
        this.lineItems = lineItems;
    }
  
	public String getName() {
		return name;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

}
