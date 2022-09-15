package com.kcsitglobal.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.kcsitglobal.model.Department;
import com.kcsitglobal.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentRepository departmentRepository;

	public Department saveDepartments(Department department) {
		return departmentRepository.save(department);
	}

	public Department findByDepartmentId(Long departmentId) {
		return departmentRepository.findByDepartmentId(departmentId);
	}

	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}
	public Department updateDeaparment(Department department, Long userId) {
		  
		return departmentRepository.save(department);
	}
	public Object deleteDepartment(Long departmentId) {
		JsonObject response = new JsonObject();
		try {
			departmentRepository.deleteById( departmentId);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		logger.info("Department Deleted Sucesfully");
		return new ResponseEntity<>("Department Deleted sucessfully", HttpStatus.OK);

	}

}
