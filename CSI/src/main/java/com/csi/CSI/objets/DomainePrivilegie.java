package com.csi.CSI.objets;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(DomainePrivilegie.class)
@Table(name = "domaineprivilegie")
@Data
public class DomainePrivilegie implements Serializable {
    @Id
    @Column
    private long dpl_abn_id;

    @Id
    @Column
    private long dpl_dom_id;

    public DomainePrivilegie() {

    }

    public DomainePrivilegie(long dpl_abn_id, long dpl_dom_id) {
        this.dpl_abn_id=dpl_abn_id;
        this.dpl_dom_id=dpl_dom_id;
    }
}
