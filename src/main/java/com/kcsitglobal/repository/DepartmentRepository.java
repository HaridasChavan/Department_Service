package com.kcsitglobal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kcsitglobal.model.Department;

public interface DepartmentRepository  extends  MongoRepository<Department, Long>{
Department findByDepartmentId(Long departmentId);
}
