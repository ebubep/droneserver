
package com.dronelab.droneserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoadEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long drone_id;


    private LocalDateTime start_date;
    private LocalDateTime end_date;
    private String operator_on_duty;


    public LoadEvent(long drone_id, String operator_on_duty, LocalDateTime start_date, LocalDateTime end_date) {

        this.drone_id = drone_id;
        this.operator_on_duty = operator_on_duty;
        this.start_date = start_date;
        this.end_date = end_date;

    }

    public LoadEvent() {
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDroneId(long drone_id) {
        this.drone_id = drone_id;
    }

    public void setOperator_on_duty(String operator_on_duty) {
        this.operator_on_duty = operator_on_duty;
    }

    public void setStartDate(LocalDateTime start_date) {
        this.start_date = start_date;
    }

    public void setEndDate(LocalDateTime end_date) {
        this.end_date = end_date;
    }


    public long getId() {
        return id;
    }

    public long getDroneId() {
        return drone_id;
    }

    public String getOperator_on_duty() {
        return operator_on_duty;
    }

    public LocalDateTime getStartDate() {
        return start_date;
    }

    public LocalDateTime getEndDate() {
        return end_date;
    }


}
