package com.oracle.dev.jdbc.springboot3.jpa.ucp;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findRoleByUser(int userId);

}