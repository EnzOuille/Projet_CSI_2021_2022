package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="news")
@Data
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long new_id;

    @Column
    private String new_texte;

    @Column
    private Date new_date_creation;

    @Column
    private String new_etat;

    @OneToOne
    private Abonne new_abn_id;

    @OneToOne
    private MotCle new_mtc_id;

    @OneToOne
    private Domaine new_dom_id;
}
