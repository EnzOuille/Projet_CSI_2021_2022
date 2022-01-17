package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="administrateur")
@Data
public class Administrateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adm_id;

    @Column
    private String adm_login;
    @Column
    private String adm_mdp;

}
