package com.springaxon.commandside.order.command;

import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.hibernate.validator.constraints.NotBlank;

import com.springaxon.common.order.model.LineItem;

/**
 * A command for creating a blog post.
 * 
 */
public class CreateOrderCommand {

    @TargetAggregateIdentifier
    private String id;
    @NotNull(message = "Title is mandatory")
    @NotBlank(message = "Title is mandatory")
    private String name;
	private Set<LineItem> lineItems;

    public CreateOrderCommand(String name, Set<LineItem> lineItems) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.lineItems = lineItems;
    }

    public String getId() {
        return id;
    }

	public String getName() {
		return name;
	}

	public Set<LineItem> getLineItems() {
		return lineItems;
	}

}