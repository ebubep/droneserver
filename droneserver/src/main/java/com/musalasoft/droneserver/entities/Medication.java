
package com.musalasoft.droneserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  
    
    private String name ;
    private double weight;
    private String code ;
    private String  image ;
    private String created_at ;
    private String updated_at  ;

    public Medication(String name, double weight, String code, String image, String created_at, String updated_at) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Medication(){
        super();
    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String getCode() {
        return code;
    }

    public String getImage() {
        return image;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Medication{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", code=" + code + ", image=" + image + ", created_at=" + created_at + ", updated_at=" + updated_at + '}';
    }
                  
    
}
