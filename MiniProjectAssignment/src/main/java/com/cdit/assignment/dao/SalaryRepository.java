package com.cdit.assignment.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdit.assignment.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
	@Query(value="SELECT * FROM SALARY s WHERE s.salary >= :minValue AND s.salary <= :maxValue", nativeQuery=true)
	public List<Salary> getSalaryWithinRange(@Param("minValue") double minValue, @Param("maxValue") double maxValue);
}