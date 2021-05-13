package model;

import lombok.Data;

import java.sql.Date;

@Data
public class Demand {
    private int id;
    private Date date;
    private int quantity;
    private String route;
    private Integer vehicleId;
    private Integer customerId;
}
