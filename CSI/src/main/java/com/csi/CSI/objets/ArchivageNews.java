package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "archivagenews")
@Data
public class ArchivageNews implements Serializable {

    @Id
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
    private long arc_mtc_1;

    @Column
    private long arc_mtc_2;

    @Column
    private long arc_mtc_3;

    @Column
    private long arc_dom_id;

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

    public long getArc_mtc_1() {
        return arc_mtc_1;
    }

    public void setArc_mtc_1(long arc_mtc_1) {
        this.arc_mtc_1 = arc_mtc_1;
    }

    public long getArc_mtc_2() {
        return arc_mtc_2;
    }

    public void setArc_mtc_2(long arc_mtc_2) {
        this.arc_mtc_2 = arc_mtc_2;
    }

    public long getArc_mtc_3() {
        return arc_mtc_3;
    }

    public void setArc_mtc_3(long arc_mtc_3) {
        this.arc_mtc_3 = arc_mtc_3;
    }

    public long getArc_dom_id() {
        return arc_dom_id;
    }

    public void setArc_dom_id(long arc_dom_id) {
        this.arc_dom_id = arc_dom_id;
    }

    public ArchivageNews() {

    }

    public ArchivageNews(News news) {
        java.util.Date utilDate = new java.util.Date();
        this.arc_id = news.getNew_id();
        this.arc_texte = news.getNew_texte();
        this.arc_date_archivage = new Date(utilDate.getTime());
        this.arc_etat = news.getNew_etat();
        this.arc_abn_id = news.getNew_abn_id();
        this.arc_mtc_1 = news.getNew_mtc_1();
        this.arc_mtc_2 = news.getNew_mtc_2();
        this.arc_mtc_3 = news.getNew_mtc_3();
        this.arc_dom_id = news.getNew_dom_id();
    }
}
