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
    private long evaluateur_id;

    @Column
    private long eval_objet;

    @Column
    private String eval_justification;

    @Column
    private Date eval_date_justification;

    public long getEval_id() {
        return eval_id;
    }

    public void setEval_id(long eval_id) {
        this.eval_id = eval_id;
    }

    public long getEvaluateur_id() {
        return evaluateur_id;
    }

    public void setEvaluateur_id(long evaluateur_id) {
        this.evaluateur_id = evaluateur_id;
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

    public Date getEval_date_justification() {
        return eval_date_justification;
    }

    public void setEval_date_justification(Date eval_date_justification) {
        this.eval_date_justification = eval_date_justification;
    }

    public AEvalue(long evaluateur_id, long new_id) {
        this.evaluateur_id = evaluateur_id;
        this.eval_objet = new_id;
    }

    public AEvalue() {
    }
}
