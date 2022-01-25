package com.csi.CSI.repositories;

import com.csi.CSI.objets.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, String>, JpaSpecificationExecutor<News> {

    @Query(
            value = "SELECT * FROM News INNER JOIN Domaine D on News.new_dom_id = D.dom_id WHERE dom_id = ?1 AND current_date - new_date_creation < 10 ORDER BY \"new_date_creation\" LIMIT 3", nativeQuery = true
    )
    List<News> findNewsByCategoryLast3(int category, int n);

    @Query(
            value = "SELECT * FROM News INNER JOIN Domaine D on News.new_dom_id = D.dom_id WHERE dom_id = ?1 AND current_date - new_date_creation < ?2 ORDER BY \"new_date_creation\"", nativeQuery = true
    )
    List<News> findNewsByCategory(int category, int n);

    @Query(
        value = "SELECT * FROM News WHERE new_mtc_1 = ?1 OR new_mtc_2 = ?1 OR new_mtc_3 = ?1 AND current_date - new_date_creation < ?2 ORDER BY \"new_date_creation\" desc LIMIT 3", nativeQuery=true
    )
    List<News> findNewsByKeywordLast3(int keyword, int n);

    @Query(
        value = "SELECT * FROM News WHERE new_mtc_1 = ?1 OR new_mtc_2 = ?1 OR new_mtc_3 = ?1 AND current_date - new_date_creation < ?2 ORDER BY \"new_date_creation\" desc", nativeQuery=true
    )
    List<News> findNewsByKeyword(int keyword, int n);

    @Query(value = "SELECT * FROM News WHERE current_date - new_date_creation < ?1 ORDER BY \"new_date_creation\"", nativeQuery = true)
    List<News> findAllActiveNews(int n);

    @Query(value = "SELECT * FROM News WHERE new_abn_id = ?1 AND current_date - new_date_creation < ?2 ORDER BY \"new_date_creation\" desc", nativeQuery = true)
    List<News> findNewsByAbonne(int abonne, int n);

    @Query(value = "SELECT * FROM News WHERE new_id = ?1 AND current_date - new_date_creation < ?2", nativeQuery = true)
    News findNewsById(long id, int n);

    @Query(value = "SELECT * FROM News WHERE current_date - new_date_creation < ?2 and new_etat LIKE  'en attente' ORDER BY \"new_date_creation\" desc", nativeQuery = true)
    List<News> findNewsToStudy(int n);

    @Query(value = "SELECT * FROM News WHERE new_id = ?1", nativeQuery = true)
    News findNewsById(long id);
}

