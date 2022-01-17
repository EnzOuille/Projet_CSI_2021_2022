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

    @OneToOne
    private Abonne ntf_abn_id;

    @Column
    private String ntf_contenu;
}
