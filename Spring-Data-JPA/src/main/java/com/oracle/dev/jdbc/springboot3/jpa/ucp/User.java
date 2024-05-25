/*
  Copyright (c) 2021, 2022, Oracle and/or its affiliates.

  This software is dual-licensed to you under the Universal Permissive License
  (UPL) 1.0 as shown at https://oss.oracle.com/licenses/upl or Apache License
  2.0 as shown at http://www.apache.org/licenses/LICENSE-2.0. You may choose
  either license.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.oracle.dev.jdbc.springboot3.jpa.ucp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="T_USERS", schema = "SYSTEM")
public class User implements Serializable{


    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "IDUSER")
    private int idUser;
    @Column(name = "LOGIN")
    private String login;
    @Column(name = "PASSWORD")
    private String password;
    
    @OneToMany( targetEntity=Command.class, mappedBy="user",fetch = FetchType.EAGER )
    private List<Command> commands = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable( name = "T_Users_Roles_Associations",
                joinColumns = @jakarta.persistence.JoinColumn( name = "IDUSER" ),
                inverseJoinColumns = @jakarta.persistence.JoinColumn( name = "IDROLE" ) )
    private List<Role> roles = new ArrayList<>();
	public User() {
	}

    public User(int id, String login, String password) {
        this.setLogin( login );
        this.setPassword( password );
        this.setId(id);
    }
    public void setId(int id) {
		this.idUser=id;
		
	}

	public int getIdUser() {
        return idUser;
    }
    
    
    public String getLogin() {
        return login;
    }
    
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return getRoles();
    }
    
    public String toString() {
        return this.idUser + ": " + this.login ;
    }

	public void setRoles(List<Role> asList) {
		this.roles=asList;
		
	}
    
}
