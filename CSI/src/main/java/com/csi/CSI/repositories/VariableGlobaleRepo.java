package com.csi.CSI.repositories;

import com.csi.CSI.objets.VariableGlobale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VariableGlobaleRepo extends CrudRepository<VariableGlobale, String> {

    @Query(value="select * from VariableGlobale where vgl_nom='N' limit 1", nativeQuery = true)
    VariableGlobale getVariableN();

    @Query(value="select * from VariableGlobale where vgl_nom='V' limit 1", nativeQuery = true)
    VariableGlobale getVariableV();

    @Query(value="select * from VariableGlobale where vgl_nom='J' limit 1", nativeQuery = true)
    VariableGlobale getVariableJ();

}

