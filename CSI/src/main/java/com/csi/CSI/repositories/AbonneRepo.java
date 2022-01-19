package com.csi.CSI.repositories;

import com.csi.CSI.objets.Abonne;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AbonneRepo extends CrudRepository<Abonne, String> {

    @Query(value="select * from abonne where abn_pseudo= ?1 and abn_mdp = ?2 limit 1", nativeQuery = true)
    Abonne getAbonneByLoginMdp(String pseudo, String mdp);

    @Query(value="select * from abonne where is_confiance = true and abn_id!= ?1", nativeQuery = true)
    List<Abonne> getAllTrustedAbonne(long id);

    @Query(value="select * from abonne where is_admin = true", nativeQuery = true)
    List<Abonne> getAllAdmin();

//    @Query(value="SELECT * from abonne where abn_id= ?1 ");

    @Query(value="SELECT * from abonne where abn_id= ?1 limit 1", nativeQuery = true)
    Abonne getAbonneById(int abn_id);
}

