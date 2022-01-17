package com.csi.CSI.repositories;

import com.csi.CSI.objets.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdministrateurRepo extends JpaRepository<Administrateur, String>, JpaSpecificationExecutor<Administrateur> {

}

