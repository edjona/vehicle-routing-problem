package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Vehicle {
   private int id;
   private String type;
   private String police_number;
   private int capacity;
   private double kilos_fuel;
   private List<Integer> routes = new ArrayList<>();
}
