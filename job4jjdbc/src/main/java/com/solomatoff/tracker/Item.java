package com.solomatoff.tracker;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime created;
    //private String[] comments;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(Integer id, String name, String description, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        //this.comments = comments;
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

    public LocalDateTime getCreated() {
        return this.created;
    }

    /*public String[] getComments() {
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
    }*/

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    /*public void setComments(String[] comments) {
        this.comments = comments;
    }*/

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
