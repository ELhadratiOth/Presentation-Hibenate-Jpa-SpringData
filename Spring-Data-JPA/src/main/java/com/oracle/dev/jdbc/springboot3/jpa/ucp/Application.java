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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ApplicationContext context= SpringApplication.run(Application.class, args);
        UserRepository userRep=context.getBean(UserRepository.class);
        CommandRepository cmdRep=context.getBean(CommandRepository.class);
        Role adminRole = new Role();
        adminRole.setRoleName("admin");
        adminRole.setIdRole(2);
        Role userRole = new Role();
        userRole.setRoleName("client");
        adminRole.setIdRole(1);
        User user1 = new User(234,"john_doe", "password123");
        User user2 = new User(345,"jane_doe", "password456");
        user1.setRoles(Arrays.asList(adminRole, userRole));
        user2.setRoles(Arrays.asList(userRole));
        userRep.saveAll(Arrays.asList(user1, user2));
        user1.setRoles(null);
        Command command1 = new Command(223,user1, new Date());
        Command command2 = new Command(458,user2, new Date());
        cmdRep.saveAll(Arrays.asList(command1, command2));
        userRep.findAll().forEach(usr->{
            System.out.println(usr.toString());
        });
        cmdRep.findAll().forEach(com->{
            System.out.println(com.toString());
        });
		
	}
    

}
