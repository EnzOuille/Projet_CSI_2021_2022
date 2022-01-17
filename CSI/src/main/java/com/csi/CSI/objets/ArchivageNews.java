package com.csi.CSI.objets;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="archivagenews")
@Data
public class ArchivageNews implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long arc_id;

    @Column
    private String arc_texte;

    @Column
    private Date arc_date_archivage;

    @Column
    private String arc_etat;

    @Column
    private long arc_abn_id;

    @Column
    private long arc_mtn_id;

    @Column
    private long arc_dom_id;
}
