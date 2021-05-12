package model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "distance")
public class Distance {
    @Id
    private int id;

    @Column(name = "from")
    private int from;

    @Column(name = "to")
    private int to;

    @Column(name = "kilometers")
    private double kilometers;
}
