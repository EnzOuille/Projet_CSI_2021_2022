package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="news")
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
    private long new_mtc_id;

    @Column
    private long new_dom_id;

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

    public long getNew_mtc_id() {
        return new_mtc_id;
    }

    public void setNew_mtc_id(long new_mtc_id) {
        this.new_mtc_id = new_mtc_id;
    }

    public long getNew_dom_id() {
        return new_dom_id;
    }

    public void setNew_dom_id(long new_dom_id) {
        this.new_dom_id = new_dom_id;
    }

    public News(){

    }
}
