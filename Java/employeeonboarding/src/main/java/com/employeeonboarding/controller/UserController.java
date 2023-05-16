package com.employeeonboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.employeeonboarding.Dto.EmployeeDto;
import com.employeeonboarding.Dto.LoginDto;
import com.employeeonboarding.model.EmployeeData;
import com.employeeonboarding.model.User;
import com.employeeonboarding.service.serviceInterface.EmployeeService;
import com.employeeonboarding.service.serviceInterface.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
		
	@Autowired
	private UserService userServe;	

	@Autowired
	EmployeeService empServe;
	

	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> register(@RequestBody User user) {
		
		String msg = "" ;
		HttpStatus status;
		try {
			msg = userServe.save(user);
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			e.getMessage();
			msg = user.getFname()+" "+user.getLname()+" not saved succesfully";
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<>(msg, status);
	}
	
	@GetMapping(value = "/userId/{username}")
	public ResponseEntity<User> getUserData(@PathVariable String username) {
		User userId = userServe.findByUsername(username);
		return new ResponseEntity<User>(userId, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/Login")
	public ResponseEntity<String> signIn(@RequestBody LoginDto user) {
		
		String msg = "";
		HttpStatus status;
		try {
			msg = userServe.findUsername(user.getUsername(),user.getPassword());
			status = HttpStatus.OK;
			
		} catch (Exception e) {
			msg =  e.getMessage();
			 status =  HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<String>(msg, status);
	}

	@PostMapping("/AddEmp")
	public ResponseEntity<String> addEmp( @RequestBody EmployeeDto emp) {
		String msg = "";
		HttpStatus status;
		User user = userServe.findByUsername(emp.getUsername());
		if(user.getRole().getName().equals("ADMIN")) {
			String data = empServe.save(emp.getEmployees());
			msg = data;
			status =  HttpStatus.OK;
		}
		else {
			msg = "Not saved data";
			status = HttpStatus.UNAUTHORIZED;
		}
		
		return new ResponseEntity<>(msg, status);
	}

	
	@GetMapping("/Emp/{username}")
	public ResponseEntity<EmployeeData> getEmployeeData(@PathVariable String username) {
		EmployeeData msg = null;
		HttpStatus status = null;
		User user = userServe.findByUsername(username);
		msg = empServe.findByEmail(user.getUsername());
		status = HttpStatus.OK;
		
		return new ResponseEntity<>(msg, status);
	}
	
	@GetMapping("/Emp/all")
	public ResponseEntity<List<EmployeeData>> getAllEmployee() {
		
		List<EmployeeData> msg = empServe.findAll();
		
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
