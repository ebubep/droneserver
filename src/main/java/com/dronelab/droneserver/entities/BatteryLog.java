
package com.dronelab.droneserver.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@Column(name = "title")
    private long drone_id;
    private int battery_level;

    public long getId() {
        return id;
    }

    public long getDroneId() {
        return drone_id;
    }

    public int getBatteryLevel() {
        return battery_level;
    }


    public BatteryLog(long drone_id, int battery_level) {
        this.drone_id = drone_id;
        this.battery_level = battery_level;
    }

    public BatteryLog() {
        super();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDroneId(long drone_id) {
        this.drone_id = drone_id;
    }

    public void setBatteryLevel(int battery_level) {
        this.battery_level = battery_level;
    }

}



