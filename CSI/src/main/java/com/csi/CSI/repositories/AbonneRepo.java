package com.csi.CSI.repositories;

import com.csi.CSI.objets.Abonne;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AbonneRepo extends CrudRepository<Abonne, String> {

    @Query(value="select * from abonne where abn_pseudo= ?1 and abn_mdp = ?2", nativeQuery = true)
    Abonne getAbonneByLoginMdp(String pseudo, String mdp);

//    @Query(value="SELECT * from abonne where abn_id= ?1 ");
}

