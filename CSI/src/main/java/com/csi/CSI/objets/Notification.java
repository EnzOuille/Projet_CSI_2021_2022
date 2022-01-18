package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ntf_id;

    @Column
    private long ntf_abn_id;

    @Column
    private String ntf_contenu;

    public long getNtf_id() {
        return ntf_id;
    }

    public void setNtf_id(long ntf_id) {
        this.ntf_id = ntf_id;
    }

    public long getNtf_abn_id() {
        return ntf_abn_id;
    }

    public void setNtf_abn_id(long ntf_abn_id) {
        this.ntf_abn_id = ntf_abn_id;
    }

    public String getNtf_contenu() {
        return ntf_contenu;
    }

    public void setNtf_contenu(String ntf_contenu) {
        this.ntf_contenu = ntf_contenu;
    }
}
