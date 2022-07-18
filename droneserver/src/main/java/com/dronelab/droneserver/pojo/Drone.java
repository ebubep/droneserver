
package com.dronelab.droneserver.pojo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ebube
 */
@Component
public class Drone {

    
    public static Drone init(String serial,String state,Integer battery, String weightLimit) {
        Drone d = new Drone();
        d.id = serial ;
        d.serial = serial;
        d.state = state;
        d.weightLimit = weightLimit; 
        d.battery = battery ;
        return d;
    }
    public  List getAllDrones(){
        
        List l = new ArrayList();
        l.add(Drone.init("DRD933","LOADING",95,"500"));
        l.add(Drone.init("SLC933","FLYING",70,"500"));
        l.add(Drone.init("GPG933","LOADING",95,"500"));
        l.add(Drone.init("KSK933","LOADING",95,"500"));
        return l;
    }
    private String id;

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(String id) {
        this.id = id;
    }


    private  String serial;
    private static String weightLimit;
    private  Integer battery;
    private  String state;
    
    /**
     * Get the value of serial
     *
     * @return the value of serial
     */
    public  String getSerial() {
        return serial;
    }

    /**
     * Set the value of serial
     *
     * @param serial new value of serial
     */
    public  void setSerial(String serial) {
        this.serial = serial;
    }
    

    /**
     * Get the value of state
     *
     * @return the value of state
     */
    public  String getState() {
        return state;
    }

    /**
     * Set the value of state
     *
     * @param state new value of state
     */
    public  void setState(String state) {
        this.state = state;
    }

    /**
     * Get the value of weightLimit
     *
     * @return the value of weightLimit
     */
    public  String getWeightLimit() {
        return weightLimit;
    }

    /**
     * Set the value of weightLimit
     *
     * @param weightLimit new value of weightLimit
     */
    public  void setWeightLimit(String weightLimit) {
        this.weightLimit = weightLimit;
    }


       

    /**
     * Get the value of battery
     *
     * @return the value of battery
     */
    public  Integer getBattery() {
        return battery;
    }

    /**
     * Set the value of battery
     *
     * @param battery new value of battery
     */
    public  void setBattery(Integer battery) {
        this.battery = battery;
    }
 
    
}
