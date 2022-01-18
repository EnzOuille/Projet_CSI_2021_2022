package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "news")
@Data
public class News implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long new_id;

    @Column
    private String new_texte;

    @Column
    private Date new_date_creation;

    @Column
    private String new_etat;

    @Column
    private long new_abn_id;

    @Column
    private long new_mtc_1;

    @Column
    private long new_mtc_2;

    @Column
    private long new_mtc_3;

    @Column
    private long new_dom_id;

    public News() {

    }

    public long getNew_id() {
        return new_id;
    }

    public void setNew_id(long new_id) {
        this.new_id = new_id;
    }

    public String getNew_texte() {
        return new_texte;
    }

    public void setNew_texte(String new_texte) {
        this.new_texte = new_texte;
    }

    public Date getNew_date_creation() {
        return new_date_creation;
    }

    public void setNew_date_creation(Date new_date_creation) {
        this.new_date_creation = new_date_creation;
    }

    public String getNew_etat() {
        return new_etat;
    }

    public void setNew_etat(String new_etat) {
        this.new_etat = new_etat;
    }

    public long getNew_abn_id() {
        return new_abn_id;
    }

    public void setNew_abn_id(long new_abn_id) {
        this.new_abn_id = new_abn_id;
    }

    public long getNew_mtc_1() {
        return new_mtc_1;
    }

    public void setNew_mtc_1(long new_mtc_1) {
        this.new_mtc_1 = new_mtc_1;
    }

    public long getNew_mtc_2() {
        return new_mtc_2;
    }

    public void setNew_mtc_2(long new_mtc_2) {
        this.new_mtc_2 = new_mtc_2;
    }

    public long getNew_mtc_3() {
        return new_mtc_3;
    }

    public void setNew_mtc_3(long new_mtc_3) {
        this.new_mtc_3 = new_mtc_3;
    }

    public long getNew_dom_id() {
        return new_dom_id;
    }

    public void setNew_dom_id(long new_dom_id) {
        this.new_dom_id = new_dom_id;
    }
}
