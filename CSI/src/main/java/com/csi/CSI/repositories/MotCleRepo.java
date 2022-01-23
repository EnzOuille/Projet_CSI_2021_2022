package com.csi.CSI.repositories;

import com.csi.CSI.objets.MotCle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MotCleRepo extends JpaRepository<MotCle, String>, JpaSpecificationExecutor<MotCle> {

    @Query(value = "select * from motsclefs where mtc_nom= ?1", nativeQuery = true)
    MotCle getMotCleBy(String mot);

    @Query(value = "select * from motsclefs where mtc_id= ?1", nativeQuery = true)
    MotCle getMotCleById(long id);
}

