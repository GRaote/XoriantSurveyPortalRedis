package com.xoriant.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.xoriant.model.EmployeeSurvey;
import com.xoriant.model.Survey;
import com.xoriant.proxyinterface.IEmployeeSurvey;

public enum EmployeeSurveyDao implements IEmployeeSurvey {
	instance;
	private static Jedis jedis = new Jedis("localhost");

	/**
	 * Adds details of survey taken by a employee.
	 * 
	 * @param empSurvey
	 */
	@Override
	public void addEmployeeSurvey(EmployeeSurvey empSurvey) {

		Map<String, String> empSurveyDetail = new HashMap<String, String>();
		empSurveyDetail.put("empSurveyId", empSurvey.getEmpSurveyId());
		empSurveyDetail.put("empId", empSurvey.getEmpId());
		empSurveyDetail.put("surveyId", empSurvey.getSurveyId());
		empSurveyDetail.put("title", empSurvey.getTitle());
		empSurveyDetail.put("finalChoice", empSurvey.getFinalChoice());
		jedis.hmset(empSurvey.getEmpSurveyId(), empSurveyDetail);
		jedis.lpush("employeesurvey", empSurvey.getEmpSurveyId());
	}

	/**
	 * Gets details of all the surveys taken by all employees.
	 * 
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Map<String, EmployeeSurvey> getAllSurveyConduced() {

		Long empSurveyLength = jedis.llen("employeesurvey");
		List<String> empSurveyList = jedis.lrange("employeesurvey", 0,
				empSurveyLength);
		Map<String, EmployeeSurvey> employeeSurveyList = new HashMap<String, EmployeeSurvey>();
		for (String empSurveyKey : empSurveyList) {
			Map<String, String> empSurvey = jedis.hgetAll(empSurveyKey);
			employeeSurveyList.put(
					empSurveyKey,
					new EmployeeSurvey(empSurvey.get("empSurveyId"), empSurvey
							.get("empId"), empSurvey.get("surveyId"), empSurvey
							.get("title"), empSurvey.get("finalChoice")));
		}
		return employeeSurveyList;
	}

	/**
	 * Gets all the surveys taken by a particular employee
	 * 
	 * @param empId
	 * @return
	 */
	@Override
	public List<Survey> getAllSurveyConducedByEmpId(String empId) {

		Map<String, EmployeeSurvey> employeeSurveyList = EmployeeSurveyDao.instance
				.getAllSurveyConduced();
		List<EmployeeSurvey> employeeSurvey = new ArrayList<>(
				employeeSurveyList.values());
		List<Survey> surveys = new ArrayList<Survey>();
		for (EmployeeSurvey emp : employeeSurvey) {
			if (emp.getEmpId().equals(empId)) {
				surveys.add(new Survey(emp.getSurveyId(), emp.getTitle(), emp
						.getFinalChoice()));
			}
		}

		return surveys;
	}
}
