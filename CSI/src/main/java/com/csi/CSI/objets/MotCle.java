package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "motsclefs")
@Data
public class MotCle {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long mtc_id;

    @Column
    private String mtc_nom;

    public long getMtc_id() {
        return mtc_id;
    }

    public void setMtc_id(long mtc_id) {
        this.mtc_id = mtc_id;
    }

    public String getMtc_nom() {
        return mtc_nom;
    }

    public void setMtc_nom(String mtc_nom) {
        this.mtc_nom = mtc_nom;
    }

    public MotCle(int id ,String texte) {
        this.mtc_nom = texte;
        this.mtc_id = id;
    }

    public MotCle() {
    }
}
