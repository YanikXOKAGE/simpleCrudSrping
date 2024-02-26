package com.example.demo.core.dal.repositories;

import com.example.demo.core.dal.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IItemRepository extends JpaRepository<Item, Long> {


    @Modifying
    @Query(value = "DELETE FROM item WHERE item_id = :itemId", nativeQuery = true)
    void deleteByItemId(@Param("itemId") Long itemId);
}
