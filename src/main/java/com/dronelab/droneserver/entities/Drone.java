
package com.dronelab.droneserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;


@Entity
public class Drone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 1, max = 100)
    private String serial_number;

    @NotNull(message = "Model is mandatory")
    private Integer model;

    @Max(500)
    private int weight_limit;

    @NotNull(message = "Battery is mandatory")
    private int battery;

    @NotNull(message = "State is mandatory")
    private int drone_state;

    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    public Drone(String serial_number, int model, int weight_limit, int battery, int state, LocalDateTime created_at, LocalDateTime updated_at) {

        this.serial_number = serial_number;
        this.model = model;
        this.weight_limit = weight_limit;
        this.battery = battery;
        this.drone_state = state;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Drone() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Drone{");
        sb.append("id=").append(id);
        sb.append(", serial_number=").append(serial_number);
        sb.append(", model=").append(model);
        sb.append(", weight_limit=").append(weight_limit);
        sb.append(", battery=").append(battery);
        sb.append(", state=").append(drone_state);
        sb.append(", created_at=").append(created_at);
        sb.append(", updated_at=").append(updated_at);
        sb.append('}');
        return sb.toString();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public void setModel(int model) {
        this.model = model;
    }

    public void setWeight(int weight_limit) {
        this.weight_limit = weight_limit;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public void setState(int state) {
        this.drone_state = state;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public int getModel() {
        return model;
    }

    public int getWeight() {
        return weight_limit;
    }

    public int getBattery() {
        return battery;
    }

    public int getState() {
        return drone_state;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }


}


