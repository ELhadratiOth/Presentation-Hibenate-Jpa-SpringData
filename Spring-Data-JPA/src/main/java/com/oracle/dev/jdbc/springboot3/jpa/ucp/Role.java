package com.oracle.dev.jdbc.springboot3.jpa.ucp;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_ROLES", schema = "SYSTEM")
public class Role implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDROLE")
    private int idRole;
	 @Column(name = "ROLENAME")
    private String roleName;
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private List<User> users;
    
    public Role() {
        this.idRole = 0;
        this.roleName = "unknown";
    }
    
    public int getIdRole() {
        return idRole;
    }
    
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    
    public String getRoleName() {
        return roleName;
    }
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    
    public List<User> getUsers() {
        return users;
    }
    
    @Override
    public String toString() {
        return "[" + this.roleName + "]";
    }
	
	

}
