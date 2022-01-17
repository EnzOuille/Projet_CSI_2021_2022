package com.csi.CSI.repositories;

import com.csi.CSI.objets.ArchivageNews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArchivageNewsRepo extends JpaRepository<ArchivageNews, String>, JpaSpecificationExecutor<ArchivageNews> {

}

