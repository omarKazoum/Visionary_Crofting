package com.visionary.crofting.repository;

import com.visionary.crofting.entity.ProductRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest,Long> {
    List<ProductRequest>  findByStockId(Long stockId);
}
