package com.employeeonboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeeonboarding.model.EmployeeData;
import com.employeeonboarding.repository.EmployeeRepository;
import com.employeeonboarding.repository.UserRepository;
import com.employeeonboarding.service.serviceInterface.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public String save(EmployeeData employee) {
		EmployeeData emps = empRepo.save(employee);
		return emps.getName()+" is Saved Successfully";
	}
	
	@Override
	public Optional<EmployeeData> findById(Long id) {
		Optional<EmployeeData> userById = findAll().stream().filter(x -> x.getId() == id).findFirst();
		return userById;
	}

	@Override
	public EmployeeData saveOrUpdate(EmployeeData EmployeeData) {
		return empRepo.saveAndFlush(EmployeeData);
	}

	@Override
	public String deleteById(Long id) {
		String message = "";
		try {
			empRepo.deleteById(id);
			userRepository.deleteById(id);
			message = "Employee deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<EmployeeData> findAll() {
		
		return empRepo.findAll();
	}

	@Override
	public EmployeeData findByEmail(String personalemail) {
		EmployeeData emp = findAll().stream().filter((x) -> x.getPeremail().equals(personalemail)).findAny().get();
		
		return emp;
	}

}
