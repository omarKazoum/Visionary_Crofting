package com.visionary.crofting.repository;

import com.visionary.crofting.entity.Supplier;
import com.visionary.crofting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Supplier findByUuid(String uuid);
    Optional<User> findByEmail(String email);
}
