package com.csi.CSI.repositories;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.DomainePrivilegie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DomainePrivilegieRepo extends CrudRepository<DomainePrivilegie, String> {

    @Query(value="SELECT * FROM domaineprivilegie WHERE dpl_abn_id = ?1", nativeQuery=true)
    List<DomainePrivilegie> findDomainesByAbonne(int abn_id);

    @Query(value="SELECT * FROM domaineprivilegie WHERE dpl_abn_id = ?1 AND dpl_dom_id = ?2 LIMIT 1", nativeQuery=true)
    DomainePrivilegie findDomaineByAbonneAndDomaine(long abn_id, long domain_id);
}
