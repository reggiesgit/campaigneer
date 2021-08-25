package com.ufpr.campaigneer.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Regis Gaboardi (@gmail.com)
 * Provided with Love and IntelliJ IDEA for campaigneer.
 * 05/06/2021
 */

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Where(clause = "deleted IS NULL")
public class BasicModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp created;
    private Timestamp updated;
    private Timestamp deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getDeleted() {
        return deleted;
    }

    public void setDeleted(Timestamp deleted) {
        this.deleted = deleted;
    }
}
