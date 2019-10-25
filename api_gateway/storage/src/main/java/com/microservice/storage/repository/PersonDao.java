package com.microservice.storage.repository;

import com.microservice.storage.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonDao extends JpaRepository<Person,Long> {
}
