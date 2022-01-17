package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="domaine")
@Data
public class Domaine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dom_id;

    @Column
    private String dom_nom;

    @Column
    private String dom_etat;

}
