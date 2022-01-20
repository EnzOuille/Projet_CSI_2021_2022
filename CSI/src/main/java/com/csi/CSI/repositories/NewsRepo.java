package com.csi.CSI.repositories;

import com.csi.CSI.objets.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {

    @Query(
            value = "SELECT * FROM News INNER JOIN Domaine D on News.new_dom_id = D.dom_id WHERE dom_id = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\" LIMIT 3", nativeQuery = true
    )
    List<News> findNewsByCategoryLast3(int category);

    @Query(
            value = "SELECT * FROM News INNER JOIN Domaine D on News.new_dom_id = D.dom_id WHERE dom_id = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\"", nativeQuery = true
    )
    List<News> findNewsByCategory(int category);

    @Query(
        value = "SELECT * FROM News WHERE new_mtc_1 = ?1 OR new_mtc_2 = ?1 OR new_mtc_3 = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\" desc LIMIT 3", nativeQuery=true
    )
    List<News> findNewsByKeywordLast3(int keyword);

    @Query(
        value = "SELECT * FROM News WHERE new_mtc_1 = ?1 OR new_mtc_2 = ?1 OR new_mtc_3 = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\" desc", nativeQuery=true
    )
    List<News> findNewsByKeyword(int keyword);

    @Query(value = "SELECT * FROM News WHERE current_date - new_date_creation < 10 ORDER BY \"new_date_creation\"", nativeQuery = true)
    List<News> findAllActiveNews();

    @Query(value = "SELECT * FROM News WHERE new_abn_id = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\"", nativeQuery = true)
    List<News> findNewsByAbonne(int abonne);
}

