package com.xoriant.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.xoriant.model.Employee;
import com.xoriant.proxyinterface.*;
/**
 * 
 * @author raote_g
 * 
 */
public enum EmployeeDao implements IEmployee {
	instance;
	private Jedis jedis = new Jedis("localhost");

	/**
	 * Returns list of employees
	 * 
	 * @return
	 */
	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = new ArrayList<Employee>();
		Long employeeLength = jedis.llen("employee");
		List<String> employeeList = jedis.lrange("employee", 0, employeeLength);
		for (String empKey : employeeList) {
			Map<String, String> employee = jedis.hgetAll(empKey);
			empList.add(new Employee(employee.get("empId"), employee
					.get("ename")));
		}

		return empList;
	}

	/**
	 * Returns employee by id
	 * 
	 * @param empId
	 * @return
	 */
	@Override
	public Employee getEmployee(String empId) {

		Map<String, String> employeeMap = jedis.hgetAll(empId);
		Employee employee = new Employee(employeeMap.get("empId"),
				employeeMap.get("ename"));
		return employee;
	}

	/**
	 * Adds employee
	 * 
	 * @param employee
	 */
	@Override
	public void addEmployee(Employee employee) {
		Map<String, String> employeeDetail = new HashMap<String, String>();
		employeeDetail.put("empId", employee.getEmpId());
		employeeDetail.put("ename", employee.getEname());
		jedis.hmset(employee.getEmpId(), employeeDetail);
		jedis.lpush("employee", employee.getEmpId());
	}
}
