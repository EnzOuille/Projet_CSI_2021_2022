package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="objetevalue")
@Data
public class ObjetEvalue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long obe_id;
}
