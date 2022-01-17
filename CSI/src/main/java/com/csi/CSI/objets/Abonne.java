package com.csi.CSI.objets;

import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.EvaluateurRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name="abonne")
public class Abonne implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private java.sql.Date abn_date_inscrit;

    @Column
    private int abn_nb_news;

    @Column
    private int abn_nb_news_valid;

    @Column
    private boolean abn_conf;

    public Abonne(){

    }

    public Abonne(long abn_id, String nom, String prenom, String email, String pseudo){
        java.util.Date utilDate = new java.util.Date();
        this.abn_nom = nom;
        this.abn_prenom = prenom;
        this.abn_email = email;
        this.abn_pseudo = pseudo;
        this.abn_date_inscrit = new Date(utilDate.getTime());
        this.abn_nb_news = 0;
        this.abn_nb_news_valid = 0;
        this.abn_conf = false;
        this.abn_id=abn_id;
    }

}
