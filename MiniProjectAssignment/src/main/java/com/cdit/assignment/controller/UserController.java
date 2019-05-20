package com.cdit.assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdit.assignment.model.JSONObjectList;
import com.cdit.assignment.model.Salary;
import com.cdit.assignment.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<JSONObjectList<Object>> salary() {
		try {
			List<Salary> results = userService.getSalaryWithinRange(0.00, 4000.00);
			JSONObjectList<Object> obj = new JSONObjectList<Object>();
			
			if(CollectionUtils.isEmpty(results)) {
				 return new ResponseEntity<JSONObjectList<Object>>(HttpStatus.NO_CONTENT);
			}
			
			obj.setResults(formatResponse(results));
			return new ResponseEntity<JSONObjectList<Object>>(obj, HttpStatus.OK);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<JSONObjectList<Object>>(HttpStatus.NO_CONTENT);
		}
	}
	
	public ArrayList<Object> formatResponse(List<Salary> salaryList) {
		try {
			ArrayList<Object> list = new ArrayList<Object>();
			
			for(Salary salary : salaryList) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("name", salary.getPerson().getName());
				data.put("salary", salary.getSalary());
				list.add(data);
			}
			
			return list;
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ArrayList<Object>();
		}
	}
}