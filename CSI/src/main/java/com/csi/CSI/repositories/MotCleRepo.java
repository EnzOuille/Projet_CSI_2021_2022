package com.csi.CSI.repositories;

import com.csi.CSI.objets.MotCle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotCleRepo extends JpaRepository<MotCle, String>, JpaSpecificationExecutor<MotCle> {

}

