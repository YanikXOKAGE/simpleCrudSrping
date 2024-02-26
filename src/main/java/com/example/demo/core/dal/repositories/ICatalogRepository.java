package com.example.demo.core.dal.repositories;


import com.example.demo.core.dal.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ICatalogRepository extends JpaRepository<Catalog, Long> {


    @Query(value = "insert into catalogitemrelation (item_id, catalog_id) values (:itemId,:catalogId)", nativeQuery = true)
    @Modifying
    @Transactional
    int addReletion(
            @Param("itemId") Long itemId, @Param("catalogId") Long catalogId);


    @Override
    List<Catalog> findAll();


    @Query(value = "insert into Catalog (catalog_name) values (:catalogName)", nativeQuery = true)
    @Modifying
    @Transactional
    int addCatalog(String catalogName);
}
