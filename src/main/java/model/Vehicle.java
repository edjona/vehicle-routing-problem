package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "availability")
    private int availability;
}
