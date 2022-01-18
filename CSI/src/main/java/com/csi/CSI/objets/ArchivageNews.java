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

    public long getArc_id() {
        return arc_id;
    }

    public void setArc_id(long arc_id) {
        this.arc_id = arc_id;
    }

    public String getArc_texte() {
        return arc_texte;
    }

    public void setArc_texte(String arc_texte) {
        this.arc_texte = arc_texte;
    }

    public Date getArc_date_archivage() {
        return arc_date_archivage;
    }

    public void setArc_date_archivage(Date arc_date_archivage) {
        this.arc_date_archivage = arc_date_archivage;
    }

    public String getArc_etat() {
        return arc_etat;
    }

    public void setArc_etat(String arc_etat) {
        this.arc_etat = arc_etat;
    }

    public long getArc_abn_id() {
        return arc_abn_id;
    }

    public void setArc_abn_id(long arc_abn_id) {
        this.arc_abn_id = arc_abn_id;
    }

    public long getArc_mtn_id() {
        return arc_mtn_id;
    }

    public void setArc_mtn_id(long arc_mtn_id) {
        this.arc_mtn_id = arc_mtn_id;
    }

    public long getArc_dom_id() {
        return arc_dom_id;
    }

    public void setArc_dom_id(long arc_dom_id) {
        this.arc_dom_id = arc_dom_id;
    }

    @Column
    private long arc_dom_id;
}
