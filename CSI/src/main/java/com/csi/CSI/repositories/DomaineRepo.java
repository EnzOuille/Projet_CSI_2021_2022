package com.csi.CSI.repositories;

import com.csi.CSI.objets.Domaine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DomaineRepo extends JpaRepository<Domaine, String>, JpaSpecificationExecutor<Domaine> {

}

