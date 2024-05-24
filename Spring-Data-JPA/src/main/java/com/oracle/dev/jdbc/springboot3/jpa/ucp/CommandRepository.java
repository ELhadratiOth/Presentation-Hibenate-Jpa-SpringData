package com.oracle.dev.jdbc.springboot3.jpa.ucp;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

interface CommandRepository extends JpaRepository<Command, Integer> {
	List<Command> findCommandByUser(User users); 

}