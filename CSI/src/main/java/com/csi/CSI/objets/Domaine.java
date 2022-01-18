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

    public long getDom_id() {
        return dom_id;
    }

    public void setDom_id(long dom_id) {
        this.dom_id = dom_id;
    }

    public String getDom_nom() {
        return dom_nom;
    }

    public void setDom_nom(String dom_nom) {
        this.dom_nom = dom_nom;
    }

    public String getDom_etat() {
        return dom_etat;
    }

    public void setDom_etat(String dom_etat) {
        this.dom_etat = dom_etat;
    }
}
