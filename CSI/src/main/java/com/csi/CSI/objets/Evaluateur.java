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

    public long getEvl_id() {
        return evl_id;
    }

    public void setEvl_id(long evl_id) {
        this.evl_id = evl_id;
    }
}
