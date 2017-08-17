package com.springaxon.common.event;

import java.io.Serializable;

public abstract class AbstractEvent implements Serializable {

	private static final long serialVersionUID = 3358011408416008394L;
	
    private String id;

    public AbstractEvent() {
    }

    public AbstractEvent(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
