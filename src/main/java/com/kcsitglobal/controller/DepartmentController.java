package com.kcsitglobal.controller;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcsitglobal.model.DbSequence;
import com.kcsitglobal.model.Department;
import com.kcsitglobal.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@PostMapping("/")
	public Department saveDepartments(@RequestBody Department department) {
		department.setDepartmentId(getSequenceNumber(department.SEQUENCE_NAME));
		return departmentService.saveDepartments(department);
	}

	@GetMapping("/{departmentId}")
	public Department findByDepartmentId(@PathVariable("departmentId") Long departmentId) {
		return departmentService.findByDepartmentId(departmentId);
	}

	@GetMapping("/show")
	public List<Department> getAllDepartment() {
		return departmentService.getAllDepartment();
	}
	@PutMapping("/{userId}")
	public Department updatedepartment(@RequestBody Department department,@PathVariable Long departmentId) {
		department.setDepartmentId(departmentId);
		return departmentService.saveDepartments(department);
	}
	@DeleteMapping("/{departmentId}")
	public Object deleteDepartment(@PathVariable Long departmentId) {
		//logger.info("Department id:" + departmentId);
		 return departmentService.deleteDepartment(departmentId);


	}

	@Autowired
	private MongoOperations mongoOperations;

	public Long getSequenceNumber(String sequenceName) {
		// get sequence no
		Query query = new Query(Criteria.where("id").is(sequenceName));
		// update the sequence no
		Update update = new Update().inc("seq", 1);
		// modify in document
		DbSequence counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true),
				DbSequence.class);

		return (long) (!Objects.isNull(counter) ? counter.getSeq() : 1);
	}

}
