
package com.dronelab.droneserver.entities;



import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class BatteryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    //@Column(name = "title")
    private long drone_id ;
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
    public BatteryLog(){
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



