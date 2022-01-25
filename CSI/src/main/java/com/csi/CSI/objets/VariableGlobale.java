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
}
