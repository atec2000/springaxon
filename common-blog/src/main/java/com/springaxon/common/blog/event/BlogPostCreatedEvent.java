package com.springaxon.common.blog.event;

import java.util.Date;

import com.springaxon.common.blog.model.BlogPostCategory;
import com.springaxon.common.event.AbstractEvent;

public class BlogPostCreatedEvent extends AbstractEvent {

	private static final long serialVersionUID = -5017075322968712692L;
	
    private String title;
    private String rawContent;
    private String publicSlug;
    private Boolean draft;
    private Boolean broadcast;
    private Date publishAt;
    private BlogPostCategory category;
    private String authorId;

    public BlogPostCreatedEvent() {

    }

    public BlogPostCreatedEvent(String id, String title, String rawContent, String publicSlug, Boolean draft, Boolean broadcast, Date publishAt,
                                BlogPostCategory category, String authorId) {
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

    public BlogPostCategory getCategory() {
        return category;
    }

    public String getAuthorId() {
        return authorId;
    }

}
