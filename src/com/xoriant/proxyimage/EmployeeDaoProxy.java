package com.xoriant.proxyimage;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.dao.EmployeeDao;
import com.xoriant.model.Employee;
import com.xoriant.proxyinterface.IEmployee;

public class EmployeeDaoProxy {
	public IEmployee getiEmployee() {
		return iEmployee;
	}

	public void setiEmployee(IEmployee iEmployee) {
		this.iEmployee = iEmployee;
	}

	private IEmployee iEmployee;
	static final Logger logger = LogManager.getLogger(EmployeeDaoProxy.class
			.getName());

	public List<Employee> getAllEmployees() {
		logger.info("In EmployeeDaoProxy");
		List<Employee> EmpList = EmployeeDao.instance.getAllEmployees();
		return EmpList;
	}

	
	public Employee getEmployee(String empId) {
		logger.info("In EmployeeDaoProxy");
		EmployeeDao.instance.getEmployee(empId);
		return null;
	}

	
	public void addEmployee(Employee employee) {
		logger.info("In EmployeeDaoProxy");
		EmployeeDao.instance.addEmployee(employee);

	}

}
