package com.csi.CSI.repositories;

import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.MotCle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DomaineRepo extends JpaRepository<Domaine, String>, JpaSpecificationExecutor<Domaine> {

    @Query(value = "select * from domaine where dom_nom= ?1 and dom_etat = 'validee'", nativeQuery = true)
    Domaine getDomaineBy(String nom);
}

