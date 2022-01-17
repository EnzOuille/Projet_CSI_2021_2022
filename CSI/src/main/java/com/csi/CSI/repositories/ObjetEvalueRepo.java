package com.csi.CSI.repositories;

import com.csi.CSI.objets.ObjetEvalue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ObjetEvalueRepo extends JpaRepository<ObjetEvalue, String>, JpaSpecificationExecutor<ObjetEvalue> {

}

