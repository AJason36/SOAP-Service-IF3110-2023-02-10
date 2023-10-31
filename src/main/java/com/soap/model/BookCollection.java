package com.soap.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "book_collection")
public class BookCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private int collectionId;

    @Column(name = "created_by")
    private User createdBy;

    @Column(name = "name")
    private String name;

    @Column(name = "desc")
    private String desc;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    private List<Book> books;
    private List<User> subscribers;

    @Override
    public String toString() {
        return "BookCollection [collectionId=" + collectionId + ", createdBy=" + createdBy + ", name=" + name
                + ", desc=" + desc + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", isDeleted="
                + isDeleted + ", deletedAt=" + deletedAt + "]";
    }
}
