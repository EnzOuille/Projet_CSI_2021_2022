package com.csi.CSI.repositories;

import com.csi.CSI.objets.AEvalue;
import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.ObjetEvalue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AEvalueRepo extends JpaRepository<AEvalue, String>, JpaSpecificationExecutor<AEvalue> {

    @Query(value="select * from aevalue where eval_objet = ?1", nativeQuery = true)
    AEvalue getAEvalueByNews(long id_news);

    @Query(value="SELECT * FROM aevalue WHERE eval_id=(SELECT max(eval_id) FROM aevalue) limit 1", nativeQuery = true)
    AEvalue getMaxId();
}

