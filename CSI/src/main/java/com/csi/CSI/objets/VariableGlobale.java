package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "variableglobale")
public class VariableGlobale implements Serializable {

    @Id
    private long vgl_id;

    @Column
    private char vgl_nom;

    @Column
    private long  vgl_valeur;

    public VariableGlobale() {

    }

    public char getVgl_nom() {
        return vgl_nom;
    }

    public long getVgl_id() {
        return vgl_id;
    }

    public long getVgl_valeur() {
        return vgl_valeur;
    }

    public void setVgl_id(long vgl_id) {
        this.vgl_id = vgl_id;
    }

    public void setVgl_nom(char vgl_nom) {
        this.vgl_nom = vgl_nom;
    }

    public void setVgl_valeur(long vgl_valeur) {
        this.vgl_valeur = vgl_valeur;
    }
}
