package com.springaxon.common.order.event;

import java.util.Date;

import com.springaxon.common.order.model.OrderCategory;
import com.springaxon.common.event.AbstractEvent;

public class OrderCreatedEvent extends AbstractEvent {

	private static final long serialVersionUID = -5017075322968712692L;
	
    private String title;
    private String rawContent;
    private String publicSlug;
    private Boolean draft;
    private Boolean broadcast;
    private Date publishAt;
    private OrderCategory category;
    private String authorId;

    public OrderCreatedEvent() {

    }

    public OrderCreatedEvent(String id, String title, String rawContent, String publicSlug, Boolean draft, Boolean broadcast, Date publishAt,
                                OrderCategory category, String authorId) {
        super(id);
        this.title = title;
        this.rawContent = rawContent;
        this.publicSlug = publicSlug;
        this.draft = draft;
        this.broadcast = broadcast;
        this.publishAt = publishAt;
        this.category = category;
        this.authorId = authorId;
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

    public Boolean isDraft() {
        return draft;
    }

    public Boolean isBroadcast() {
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
