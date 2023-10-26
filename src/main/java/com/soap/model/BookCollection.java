package com.soap.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookCollection {
    public int collectionId;
    public User createdBy;
    public String name;
    public String desc;
    public Timestamp createdAt;
    public Timestamp updatedAt;
    public boolean isDeleted;
    public Timestamp deletedAt;
    public List<Book> books;
    public List<User> subscribers;

    @Override
    public String toString() {
        return "BookCollection [collectionId=" + collectionId + ", createdBy=" + createdBy + ", name=" + name
                + ", desc=" + desc + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isDeleted="
                + isDeleted + ", deletedAt=" + deletedAt + "]";
    }
}
