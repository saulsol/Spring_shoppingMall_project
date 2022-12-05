package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // JPARepository 인터페이스를 상속받으면 알아서 빈 등록

    // findBy ---> 조회기능
    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail( @Param("itemDetail") String itemDetail);

    @Query(value = "SELECT * FROM item i WHERE i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative( @Param("itemDetail") String itemDetail);

}
