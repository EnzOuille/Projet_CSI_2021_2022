package com.csi.CSI.repositories;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.MotCle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DomaineRepo extends JpaRepository<Domaine, String>, JpaSpecificationExecutor<Domaine> {

    @Query(value = "select * from domaine where dom_nom= ?1 and dom_etat = 'valide'", nativeQuery = true)
    Domaine getDomaineBy(String nom);

    @Query(value = "select * from domaine where dom_id= ?1 and dom_etat = 'valide'", nativeQuery = true)
    Domaine getDomaineById(long id);

    @Query(value = "select * from domaine where dom_id= ?1", nativeQuery = true)
    Domaine getDomaineOnlyById(long id);

    @Query(value = "select * from domaine where dom_etat = 'valide'", nativeQuery = true)
    List<Domaine> findAllActive();

    @Query(value = "SELECT * FROM domaine WHERE dom_etat LIKE 'en attente'", nativeQuery = true)
    List<Domaine> getDomToStudy();

    @Query(value="SELECT * FROM domaine WHERE dom_id=(SELECT max(dom_id) FROM domaine) limit 1", nativeQuery = true)
    Domaine getMaxId();
}

