package com.csi.CSI.repositories;

import com.csi.CSI.objets.Abonne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AbonneRepo extends JpaRepository<Abonne, String>, JpaSpecificationExecutor<Abonne> {

}

