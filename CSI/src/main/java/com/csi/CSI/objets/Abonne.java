package com.csi.CSI.objets;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "abonne")
public class Abonne implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long abn_id;

    @Column
    private String abn_nom;

    @Column
    private String abn_prenom;

    @Column
    private String abn_email;

    @Column
    private String abn_pseudo;

    @Column
    private String abn_mdp;

    @Column
    private boolean abn_admin;

    @Column
    private java.sql.Date abn_date_inscrit;

    @Column
    private int abn_nb_news;

    @Column
    private int abn_nb_news_valid;

    @Column
    private boolean abn_conf;

    public Abonne() {

    }

    public Abonne(String nom, String prenom, String email, String pseudo, String abn_mdp) {
        java.util.Date utilDate = new java.util.Date();
        this.abn_nom = nom;
        this.abn_prenom = prenom;
        this.abn_email = email;
        this.abn_pseudo = pseudo;
        this.abn_date_inscrit = new Date(utilDate.getTime());
        this.abn_nb_news = 0;
        this.abn_nb_news_valid = 0;
        this.abn_conf = false;
        this.abn_mdp = abn_mdp;
    }

    public Abonne(Long id, String nom, String prenom, String email, String pseudo, String abn_mdp) {
        java.util.Date utilDate = new java.util.Date();
        this.abn_id = id;
        this.abn_nom = nom;
        this.abn_prenom = prenom;
        this.abn_email = email;
        this.abn_pseudo = pseudo;
        this.abn_date_inscrit = new Date(utilDate.getTime());
        this.abn_nb_news = 0;
        this.abn_nb_news_valid = 0;
        this.abn_conf = false;
        this.abn_mdp = abn_mdp;
    }

    public long getAbn_id() {
        return abn_id;
    }

    public void setAbn_id(long abn_id) {
        this.abn_id = abn_id;
    }

    public String getAbn_nom() {
        return abn_nom;
    }

    public void setAbn_nom(String abn_nom) {
        this.abn_nom = abn_nom;
    }

    public String getAbn_prenom() {
        return abn_prenom;
    }

    public void setAbn_prenom(String abn_prenom) {
        this.abn_prenom = abn_prenom;
    }

    public String getAbn_email() {
        return abn_email;
    }

    public void setAbn_email(String abn_email) {
        this.abn_email = abn_email;
    }

    public String getAbn_pseudo() {
        return abn_pseudo;
    }

    public void setAbn_pseudo(String abn_pseudo) {
        this.abn_pseudo = abn_pseudo;
    }

    public String getAbn_mdp() {
        return abn_mdp;
    }

    public void setAbn_mdp(String abn_mdp) {
        this.abn_mdp = abn_mdp;
    }

    public boolean isAbn_admin() {
        return abn_admin;
    }

    public void setAbn_admin(boolean abn_admin) {
        this.abn_admin = abn_admin;
    }


    public Date getAbn_date_inscrit() {
        return abn_date_inscrit;
    }

    public void setAbn_date_inscrit(Date abn_date_inscrit) {
        this.abn_date_inscrit = abn_date_inscrit;
    }

    public int getAbn_nb_news() {
        return abn_nb_news;
    }

    public void setAbn_nb_news(int abn_nb_news) {
        this.abn_nb_news = abn_nb_news;
    }

    public int getAbn_nb_news_valid() {
        return abn_nb_news_valid;
    }

    public void setAbn_nb_news_valid(int abn_nb_news_valid) {
        this.abn_nb_news_valid = abn_nb_news_valid;
    }

    public boolean isAbn_conf() {
        return abn_conf;
    }

    public void setAbn_conf(boolean abn_conf) {
        this.abn_conf = abn_conf;
    }

    public void verifConf(long N, long V, int nbNotStudy) {
        this.abn_conf = this.abn_nb_news >= N && this.abn_nb_news_valid / ((float) (this.abn_nb_news - nbNotStudy)) * 100 >= V;
    }
}
