package com.csi.CSI.objets;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="administrateur")
@Data
public class Administrateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long adm_id;

    @Column
    private String adm_login;
    @Column
    private String adm_mdp;

    public long getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(long adm_id) {
        this.adm_id = adm_id;
    }

    public String getAdm_login() {
        return adm_login;
    }

    public void setAdm_login(String adm_login) {
        this.adm_login = adm_login;
    }

    public String getAdm_mdp() {
        return adm_mdp;
    }

    public void setAdm_mdp(String adm_mdp) {
        this.adm_mdp = adm_mdp;
    }
}
