package com.xoriant.proxyinterface;

import java.util.List;

import com.xoriant.model.Employee;

public interface IEmployee {
	public List<Employee> getAllEmployees();

	public Employee getEmployee(String empId);

	public void addEmployee(Employee employee);
}
