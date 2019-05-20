package com.cdit.assignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdit.assignment.dao.PersonRepository;
import com.cdit.assignment.dao.SalaryRepository;
import com.cdit.assignment.model.Person;
import com.cdit.assignment.model.Salary;

@Service
public class UserService {
	@Autowired
	private PersonRepository personRepo;
	
	@Autowired
	private SalaryRepository salaryRepo;
	
	public void savePerson(Person person) {
		personRepo.save(person);
	}
	
	public List<Person> getAllPerson() {
		return personRepo.findAll();
	}
	
	public void saveSalary(Salary salary) {
		salaryRepo.save(salary);
	}
	
	public List<Salary> getAllSalary() {
		return salaryRepo.findAll();
	}
	
	public List<Salary> getSalaryWithinRange(double minValue, double maxValue) {
		return salaryRepo.getSalaryWithinRange(minValue, maxValue);
	}
}