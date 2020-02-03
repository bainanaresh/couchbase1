package com.baina.couchbase;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baina.couchbase.repository.EmployeeRepository;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.baina")
public class Couchbase1Application {

	@Autowired
	private EmployeeRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Couchbase1Application.class, args);
	}

	@PostConstruct
	public void initEmployees() {
		repo.saveAll(Stream
				.of(new Employee(1,"naresh", new String[] { "vvraopet", "hyderabad", "borabanda" }),
						new Employee(2,"ranga", new String[] { "miryalaguda", "hyderabad", "borabanda" }),
						new Employee(3,"vamshi", new String[] { "vempally", "hyderabad", "borabanda" }),
						new Employee(4,"swarup", new String[] { "telagawaram", "hyderabad", "borabanda" }))
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/emp/{id}")
	public Optional<Employee> getEmployee(@PathVariable("id") int id) {
		Optional<Employee> emp=repo.findById(id);
		System.out.println(emp.getClass().getName());
		return emp;
	}
	
	@GetMapping("/emps")
	public Iterable<Employee> getAllEmps(){
		return repo.findAll();
	}
	
	@PostMapping("/saveemp")
	public String saveEmployee(@RequestBody Employee emp) {
		
		repo.save(emp);
		
		return "data saved successfully";
		
	}
	
	@GetMapping("/empbyname")
	public List<Employee> getEmployeeByname() {
		List<Employee> emp=repo.findByNameLike();
		System.out.println(emp.getClass().getName());
		return emp;
	}
	

}
