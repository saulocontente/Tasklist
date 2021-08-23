package com.aulaudemy.tasklist.model;

import java.io.Serializable;

public class Task implements Serializable {
    protected int id;
    protected String description;
    protected boolean status;

    public Task() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
