package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="motsclefs")
@Data
public class MotCle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mtc_id;

    @Column
    private String mtc_nom;
}
