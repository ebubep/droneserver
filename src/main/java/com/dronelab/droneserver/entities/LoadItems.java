
package com.dronelab.droneserver.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoadItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long event_id;
    private String med_code;
    private int qty;
    private LocalDateTime created_at;

    public LoadItems(long eventId, String medCode, int qty, LocalDateTime createdAt) {

        this.event_id = eventId;
        this.med_code = medCode;
        this.qty = qty;
        this.created_at = createdAt;
    }

    public LoadItems() {
        super();
    }

    public long getId() {
        return id;
    }

    public long getEventId() {
        return event_id;
    }

    public String getMedCode() {
        return med_code;
    }

    public int getQty() {
        return qty;
    }

    public LocalDateTime getCreatedAt() {
        return created_at;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEventId(long eventId) {
        this.event_id = eventId;
    }

    public void setMedId(String medCode) {
        this.med_code = medCode;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.created_at = createdAt;
    }

}
