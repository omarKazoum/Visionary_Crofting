package com.visionary.crofting.repository;

import com.visionary.crofting.entity.Client;
import com.visionary.crofting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {

    Client findByUuid(String uuid);

    Optional<User> findByEmail(String email);
}
