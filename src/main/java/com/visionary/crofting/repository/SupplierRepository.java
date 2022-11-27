package com.visionary.crofting.repository;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
