package com.csi.CSI.repositories;

import com.csi.CSI.objets.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepo extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {

}

