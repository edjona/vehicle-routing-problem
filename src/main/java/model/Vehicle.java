package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vehicle {
   private String policeNumber;
   private int capacity;
   private double costPerKilometer;
   private List<Integer> routes = new ArrayList<>();
}
