package com.csi.CSI.repositories;

import com.csi.CSI.objets.News;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {

    @Query(
            value = "SELECT * FROM News INNER JOIN Domaine D on News.new_dom_id = D.dom_id WHERE dom_id = ?1 LIMIT 3", nativeQuery = true
    )
    List<News> findNewsByCategory(int category);
}

