package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="aevalue")
@Data
public class AEvalue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eval_id;

    @OneToOne
    private Evaluateur evaluateur_id;

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

    public Evaluateur getEvaluateur_id() {
        return evaluateur_id;
    }

    public void setEvaluateur_id(Evaluateur evaluateur_id) {
        this.evaluateur_id = evaluateur_id;
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
}
