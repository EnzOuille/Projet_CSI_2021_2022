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
}
