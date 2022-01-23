package com.csi.CSI.objets;

import java.util.Date;

public class NewsDisplay {

    public long id;

    public String texte;

    public Date date_creation;

    public String etat;

    public String mtc1;

    public String mtc2;

    public String mtc3;

    public String abn;

    public String dom;

    public NewsDisplay(News temp, String mtc1, String mtc2, String mtc3, String abn, String dom) {
        this.id = temp.getNew_id();
        this.texte = temp.getNew_texte();
        this.date_creation = temp.getNew_date_creation();
        this.etat = temp.getNew_etat();
        this.mtc1 = mtc1;
        this.mtc2 = mtc2;
        this.mtc3 = mtc3;
        this.abn = abn;
        this.dom = dom;
    }

    public NewsDisplay(ArchivageNews temp, String mtc1, String mtc2, String mtc3, String abn, String dom) {
        this.id = temp.getArc_id();
        this.texte = temp.getArc_texte();
        this.date_creation = temp.getArc_date_archivage();
        this.etat = temp.getArc_etat();
        this.mtc1 = mtc1;
        this.mtc2 = mtc2;
        this.mtc3 = mtc3;
        this.abn = abn;
        this.dom = dom;
    }

}
