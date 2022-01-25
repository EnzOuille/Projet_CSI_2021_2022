package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "aevalue")
@Data
public class AEvalue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eval_id;

    @Column
    private long eval_abn_id;

    @Column
    private long eval_objet;

    @Column
    private String eval_justification;

    @Column
    private Date eval_date_justifcation;

    public long getEval_id() {
        return eval_id;
    }

    public void setEval_id(long eval_id) {
        this.eval_id = eval_id;
    }

    public long getEval_abn_id() {
        return eval_abn_id;
    }

    public void setEval_abn_id(long evaluateur_id) {
        this.eval_abn_id = evaluateur_id;
    }

    public long getEval_objet() {
        return eval_objet;
    }

    public void setEval_objet(long eval_objet) {
        this.eval_objet = eval_objet;
    }

    public String getEval_justification() {
        return eval_justification;
    }

    public void setEval_justification(String eval_justification) {
        this.eval_justification = eval_justification;
    }

    public Date getEval_date_justifcation() {
        return eval_date_justifcation;
    }

    public void setEval_date_justifcation(Date eval_date_justification) {
        this.eval_date_justifcation = eval_date_justification;
    }

    public AEvalue(long evaluateur_id, long new_id) {
        this.eval_abn_id = evaluateur_id;
        this.eval_objet = new_id;
        this.eval_justification = null;
        this.eval_date_justifcation = null;
    }

    public AEvalue() {
    }

    public void update(long new_id, String justification) {
        java.util.Date utilDate = new java.util.Date();
        this.eval_objet = new_id;
        this.eval_justification = justification;
        this.eval_date_justifcation = new Date(utilDate.getTime());
    }
}
