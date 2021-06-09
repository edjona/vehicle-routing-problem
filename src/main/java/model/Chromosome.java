package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Chromosome {
    private int id;
    private List<Vehicle> vehicles = new ArrayList<>();
}
