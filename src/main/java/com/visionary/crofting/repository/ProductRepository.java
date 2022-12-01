package com.visionary.crofting.repository;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByReference(String reference);
}
