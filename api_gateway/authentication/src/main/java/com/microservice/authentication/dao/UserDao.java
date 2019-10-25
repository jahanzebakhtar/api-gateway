package com.microservice.authentication.dao;

import com.microservice.authentication.model.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<Users, String> {
}
