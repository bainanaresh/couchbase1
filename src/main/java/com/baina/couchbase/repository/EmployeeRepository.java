package com.baina.couchbase.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import com.baina.couchbase.Employee;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "employee",viewName = "all")
public interface EmployeeRepository extends CouchbaseRepository<Employee,Integer> {
	
	@Query("#{#n1ql.selectEntity} WHERE name= 'naresh'")
	List<Employee> findByNameLike();

}
