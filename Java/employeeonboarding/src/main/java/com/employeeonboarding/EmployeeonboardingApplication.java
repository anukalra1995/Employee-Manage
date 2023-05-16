package com.employeeonboarding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.employeeonboarding.Constants.ConstantUtils;
import com.employeeonboarding.model.*;
import com.employeeonboarding.service.serviceInterface.*;

@SpringBootApplication
public class EmployeeonboardingApplication implements CommandLineRunner  {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeonboardingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleService.findAll().isEmpty()) {
			roleService.saveOrUpdate(new Roles(ConstantUtils.ADMIN.toString()));
			roleService.saveOrUpdate(new Roles(ConstantUtils.USER.toString()));
		}

		if (userService.findAll().isEmpty()) {
			User user1 = new User();
			user1.setUsername("test@user.com");
			user1.setFname("Test");
			user1.setLname("User");
			user1.setRole(roleService.findByName(ConstantUtils.USER.toString()));
			user1.setPwd(new BCryptPasswordEncoder().encode("testuser"));
			userService.saveOrUpdate(user1);

			User user2 = new User();
			user2.setUsername("test@admin.com");
			user2.setFname("Test");
			user2.setLname("Admin");
			user2.setRole(roleService.findByName(ConstantUtils.ADMIN.toString()));
			user2.setPwd(new BCryptPasswordEncoder().encode("testadmin"));
			userService.saveOrUpdate(user2);
		}

		
	}
}
