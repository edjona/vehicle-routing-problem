package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@Table(name = "demand")
public class Demand {
    @Id
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "route")
    private String route;

    @Column(name = "id_vehicle")
    private Integer vehicleId;

    @Column(name = "id_customer")
    private Integer customerId;
}
