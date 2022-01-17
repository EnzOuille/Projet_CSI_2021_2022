package com.csi.CSI.repositories;

import com.csi.CSI.objets.AEvalue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AEvalueRepo extends JpaRepository<AEvalue, String>, JpaSpecificationExecutor<AEvalue> {

}

