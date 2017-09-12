package com.springaxon.commandside.order.vo.req;

import java.util.Set;

import com.springaxon.common.order.model.LineItem;

public class CreateOrderRequest {

	private String name;
	private Set<LineItem> lineItems;

    public CreateOrderRequest() {
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(Set<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
}
