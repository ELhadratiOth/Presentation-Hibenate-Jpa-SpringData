package com.oracle.dev.jdbc.springboot3.jpa.ucp;

public interface UserCustomRepository {
	//déclaration des méthodes personalisés
	//AUutant que vous voulez
	void assignRoleToUser(User u,String roleName);

}
