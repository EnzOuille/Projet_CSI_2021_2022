package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name="abonne")
public class Abonne implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private Date abn_date_inscrit;

    @Column
    private int abn_nb_news;

    @Column
    private int abn_nb_news_valid;

    @Column
    private boolean abn_conf;

    public Abonne(){

    }

    public Abonne(String nom, String prenom, String email, String pseudo, Date date, int news, int news_valid, boolean conf){
        this.abn_nom = nom;
        this.abn_prenom = prenom;
        this.abn_email = email;
        this.abn_pseudo = pseudo;
        this.abn_date_inscrit = date;
        this.abn_nb_news = news;
        this.abn_nb_news_valid = news_valid;
        this.abn_conf = conf;
    }

}
