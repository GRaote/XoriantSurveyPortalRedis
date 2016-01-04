package com.xoriant.resources;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.model.EmployeeSurvey;
import com.xoriant.model.Survey;
import com.xoriant.model.SurveyResult;
import com.xoriant.proxyimage.EmployeeSurveyDaoProxy;
import com.xoriant.proxyimage.SurveyDaoProxy;
import com.xoriant.proxyinterface.IEmployeeSurvey;
import com.xoriant.proxyinterface.ISurvey;

/**
 * 
 * @author raote_g
 * 
 */
@Path("/")
public class EmployeeSurveyResource {
	static final Logger logger = LogManager
			.getLogger(EmployeeSurveyResource.class.getName());

	/**
	 * Add details of a survey taken by the employee to the database table
	 * EmployeeSurvey
	 * 
	 * @param employeeSurvey
	 * @throws IOException
	 * @throws SQLException
	 */
	@POST
	@Path("AddSurvey")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void storeEmployeeSurvey(EmployeeSurvey employeeSurvey)
			throws IOException, SQLException {
		logger.info("In storeEmployeeSurvey");
		IEmployeeSurvey empsur = new EmployeeSurveyDaoProxy();
		empsur.addEmployeeSurvey(employeeSurvey);
	}

	/**
	 * Gets all the surveys taken by all the employees
	 * 
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 */
	@GET
	@Path("SurveyResult")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<EmployeeSurvey> getAllSurveyResultList(
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws SQLException {
		List<EmployeeSurvey> EmpList = new ArrayList<EmployeeSurvey>();
		Map<String, EmployeeSurvey> employeeSurveyList = null;
		IEmployeeSurvey empsur = new EmployeeSurveyDaoProxy();
		try {
			employeeSurveyList = empsur.getAllSurveyConduced();
		} catch (Exception e) {
			throw new NotAuthorizedException("Employee Surveys not found",
					servletResponse);
		}
		EmpList.addAll(employeeSurveyList.values());
		return EmpList;
	}

	/**
	 * Gets surveys take by survey id
	 * 
	 * @param surveyId
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 */
	@GET
	@Path("SurveyResult/{SurveyId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public SurveyResult getAllSurveyResultById(
			@PathParam("SurveyId") String surveyId,
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws SQLException {
		logger.info("In getAllSurveyResultById");
		IEmployeeSurvey empsur = new EmployeeSurveyDaoProxy();
		ISurvey surr = new SurveyDaoProxy();
		Survey surveyTemplate = null;
		int countChoice1 = 0, countChoice2 = 0;
		Map<String, Survey> surveyList = null;
		try {
			surveyList = surr.getAllSurvey();
		} catch (Exception e) {
			throw new NotAuthorizedException("Surveys not found",
					servletResponse);
		}
		surveyTemplate = surveyList.get(surveyId);
		Map<String, EmployeeSurvey> employeeSurveyList = null;
		try {
			employeeSurveyList = empsur.getAllSurveyConduced();
		} catch (Exception e) {
			throw new NotAuthorizedException("Employee Surveys not found",
					servletResponse);
		}
		List<EmployeeSurvey> empList = new ArrayList<EmployeeSurvey>();
		empList.addAll(employeeSurveyList.values());

		for (EmployeeSurvey survey : empList) {
			if (survey.getSurveyId().equals(surveyId)) {

				if (survey.getFinalChoice().equals(surveyTemplate.getChoice1())) {
					countChoice1++;
				} else {
					countChoice2++;
				}

			}
		}
		SurveyResult sur = null;

		sur = new SurveyResult(surveyId, surveyTemplate.getChoice1(),
				countChoice1, surveyTemplate.getChoice2(), countChoice2);
		return sur;

	}

	/**
	 * Gets survey taken by employee id
	 * 
	 * @param empId
	 * @param servletResponse
	 * @param servletRequest
	 * @return
	 */
	@GET
	@Path("AttemptedSurvey/{empId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,
			MediaType.TEXT_HTML })
	public List<Survey> getAttemptedSurveyListByEmployee(
			@PathParam("empId") String empId,
			@Context HttpServletResponse servletResponse,
			@Context HttpServletRequest servletRequest) throws SQLException {
		logger.info("In getAttemptedSurveyResultByEmployee");
		List<Survey> surveys = null;
		IEmployeeSurvey empsur = new EmployeeSurveyDaoProxy();
		try {
			surveys = empsur.getAllSurveyConducedByEmpId(empId);
		} catch (Exception e) {
			throw new NotAuthorizedException("Surveys not found",
					servletResponse);
		}
		return surveys;

	}
}
