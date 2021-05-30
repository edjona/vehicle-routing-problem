package model;

import lombok.Data;

@Data
public class Vehicle {
    private int id;
    private String type;
    private String vehicle_police_id;
    private int capacity;
    private double kilos_fuel;
}
