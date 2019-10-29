package com.microservice.gateway.dao;

import com.microservice.gateway.model.UserRoles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends CrudRepository<UserRoles, Integer> {
}