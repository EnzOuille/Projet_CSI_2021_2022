package com.csi.CSI.repositories;

import com.csi.CSI.objets.Evaluateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EvaluateurRepo extends JpaRepository<Evaluateur, String>, JpaSpecificationExecutor<Evaluateur> {

}

