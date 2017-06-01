package com.photoexchange.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.photoexchange.model.Role;

public interface RoleDao extends JpaRepository<Role, Long>{
}
