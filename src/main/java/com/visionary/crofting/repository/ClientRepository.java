package com.visionary.crofting.repository;

import com.visionary.crofting.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

   // Client findByUuid(String uuid);
}
