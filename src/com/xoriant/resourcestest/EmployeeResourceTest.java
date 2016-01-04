package com.xoriant.resourcestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.xoriant.model.Employee;
import com.xoriant.proxyimage.EmployeeDaoProxy;
import com.xoriant.proxyinterface.IEmployee;

/**
 * 
 * @author raote_g
 * 
 */
public class EmployeeResourceTest {

	private EmployeeDaoProxy empDaoProxy = null;
	private List<Employee> empList = null;
	private IEmployee iEmployee = null;

	/**
	 * 
	 */
	@Before
	public void setup() {
		iEmployee = mock(IEmployee.class);
		empList = new ArrayList<Employee>();
		empList.add(new Employee("e01", "Gauu"));
		empList.add(new Employee("e05", "Gaurang"));
		empDaoProxy = new EmployeeDaoProxy();
		empDaoProxy.setiEmployee(iEmployee);
	}

	/**
	 * 
	 */
	@Test
	public void getAllEmployeesNullTest() {
		boolean gotException = true;

		try {
			iEmployee.getAllEmployees();
		} catch (NullPointerException e) {
			System.out.println("Null pointer exception");
			gotException = false;
		}

		if (!gotException) {
			fail("Returned null");
		}
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getEmployeeSuccessTest() {
		Mockito.when(iEmployee.getAllEmployees()).thenReturn(empList);
		List<Employee> employeeList = null;
		employeeList = empDaoProxy.getAllEmployees();
		Employee emp = employeeList.get(0);
		Employee emp1 = iEmployee.getAllEmployees().get(0);
		assertEquals(emp, emp1);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getEmployeeFailTest() {
		Mockito.when(iEmployee.getAllEmployees()).thenReturn(empList);
		List<Employee> employeeList = null;
		employeeList = empDaoProxy.getAllEmployees();
		Employee emp = employeeList.get(0);
		Employee emp1 = iEmployee.getAllEmployees().get(1);
		boolean flag = emp.equals(emp1);
		assertFalse(flag);
	}

}
