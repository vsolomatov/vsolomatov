package com.solomatoff.tracker;

import java.util.Date;

public class Item {
    private Integer id;
    private String name;
    private String description;
    private Date created;
    private String[] comments;

    public Item(Integer id, String name, String description, Date created, String[] comments) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.comments = comments;
    }

    public Integer getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public Date getCreated() {
        return this.created;
    }

    public String[] getComments() {
        return comments;
    }

    public String commentsToString() {
        StringBuilder resultBuilder = new StringBuilder("{");
        for (String comment : this.comments) {
            resultBuilder.append(comment).append(", ");
        }
        String result = resultBuilder.toString();
        result += "}";
        return result;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }


}
