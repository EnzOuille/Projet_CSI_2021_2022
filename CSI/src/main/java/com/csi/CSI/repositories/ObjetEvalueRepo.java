package com.csi.CSI.repositories;

import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.ObjetEvalue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ObjetEvalueRepo extends JpaRepository<ObjetEvalue, String>, JpaSpecificationExecutor<ObjetEvalue> {

    @Query(value="SELECT * FROM objetevalue WHERE obe_id=(SELECT max(obe_id) FROM objetevalue) limit 1", nativeQuery = true)
    ObjetEvalue getMaxId();
}

