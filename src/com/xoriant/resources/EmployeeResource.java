package com.xoriant.resources;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.model.Employee;
import com.xoriant.proxyimage.EmployeeDaoProxy;

/**
 * 
 * @author raote_g
 * 
 */
@Path("Employees")
public class EmployeeResource {
	static final Logger logger = LogManager.getLogger(EmployeeResource.class
			.getName());

	/**
	 * Adds an employee from the form to the database table Employee
	 * 
	 * @param emp
	 * @throws SQLException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void registerEmployeeForSurvey(Employee emp) {
		logger.info("In registerEmployeeForSurvey");
		EmployeeDaoProxy employee = new EmployeeDaoProxy();
		employee.addEmployee(emp);
	}

	/**
	 * Gets the list of all employees from the database
	 * 
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Employee> getEmployeeList(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) {
		logger.info("In getEmployeeList");
		List<Employee> EmpList = null;
		EmployeeDaoProxy employee = new EmployeeDaoProxy();
		try {
			EmpList = employee.getAllEmployees();
		} catch (Exception e) {
			throw new NotAuthorizedException("No Employees found");
		}
		return EmpList;
	}
}
