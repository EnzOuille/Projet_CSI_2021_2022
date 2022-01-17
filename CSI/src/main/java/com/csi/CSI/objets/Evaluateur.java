package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="evaluateur")
@Data
public class Evaluateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long evl_id;
}
