package com.cdit.assignment.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdit.assignment.model.JSONObjectList;
import com.cdit.assignment.model.Salary;
import com.cdit.assignment.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public ResponseEntity<JSONObjectList<Salary>> salary() {
		try {
			List<Salary> results = userService.getSalaryWithinRange(0.00, 4000.00);
			JSONObjectList<Salary> obj = new JSONObjectList<Salary>();
			
			if(CollectionUtils.isEmpty(results)) {
				 return new ResponseEntity<JSONObjectList<Salary>>(HttpStatus.NO_CONTENT);
			}
			
			obj.setResults(results);
			return new ResponseEntity<JSONObjectList<Salary>>(obj, HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}