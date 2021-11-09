package com.prova.api.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
