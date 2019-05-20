package com.cdit.assignment.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.cdit.assignment.model.Person;
import com.cdit.assignment.model.Salary;
import com.cdit.assignment.service.UserService;

@Component
public class UserBackingBean {
	private static final String FILE_PATH = "classpath:\\samples\\salary.csv";
	private static final String DELIMITER = ",";
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		try {
			System.out.println("UserBackingBean::init>> Start");
			initRecords();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("UserBackingBean::init>> End");
		}
	}
	
	public List<Salary> initRecords() {
		try {
			System.out.println("UserBackingBean::initRecords>> Start");
			File file = ResourceUtils.getFile(FILE_PATH);
			System.out.println("File Name: " + file.getName());
			System.out.println("File Path: " + file.getAbsolutePath());
			
			List<String> records = readFile(file);
			System.out.println(records.size() + " record(s) found");
			
			List<Salary> salaryList = new ArrayList<Salary>();
			
			for(String record : records) {
				String[] data = record.split(DELIMITER);
				System.out.println("Record: " + record);
				
				Person person = new Person(data[0]);
				boolean personSaved = savePerson(person);
				
				if(personSaved) {
					System.out.println(person.getName() + " record successfully saved to database");
					Salary salary = new Salary(person, Double.valueOf(data[1]));
					boolean salarySaved = saveSalary(salary);
					
					if(salarySaved) {
						System.out.println(salary.getPerson().getName() + " salary record successfully saved to database");
						salaryList.add(salary);
					}
					else {
						System.out.println("Error occurred while saving " + salary.getPerson().getName() + " salary record");
					}
				}
				else {
					System.out.println("Error occurred while saving " + person.getName() + " salary record");
				}
			}
			
			return salaryList;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			System.out.println("UserBackingBean::initRecords>> End");
		}
	}
	
	public List<String> readFile(File file) {
		System.out.println("UserBackingBean::readFile>> Start");
		Scanner scan = null;
		List<String> records = new ArrayList<String>();
		
		try {
			scan = new Scanner(file);
			
			while(scan.hasNextLine()) {
				records.add(scan.nextLine());
			}
			
			return records;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			scan.close();
			System.out.println("UserBackingBean::readFile>> End");
		}
	}
	
	public boolean savePerson(Person person) {
		try {
			System.out.println("UserBackingBean::savePerson>> Start");
			userService.savePerson(person);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			System.out.println("UserBackingBean::savePerson>> End");
		}
	}
	
	public boolean saveSalary(Salary salary) {
		try {
			System.out.println("UserBackingBean::saveSalary>> Start");
			userService.saveSalary(salary);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			System.out.println("UserBackingBean::saveSalary>> End");
		}
	}
}