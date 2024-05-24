package com.oracle.dev.jdbc.springboot3.jpa.ucp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class UserCustomRepositoryImpl implements UserCustomRepository {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void assignRoleToUser(User u,String roleName){
		//Role newRole=new Role();
		//if(roleName)

	    // ...implémentation de le methode
	}

}
