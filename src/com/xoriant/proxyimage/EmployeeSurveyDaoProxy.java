package com.xoriant.proxyimage;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.dao.EmployeeSurveyDao;
import com.xoriant.model.EmployeeSurvey;
import com.xoriant.model.Survey;
import com.xoriant.proxyinterface.IEmployeeSurvey;

public class EmployeeSurveyDaoProxy implements IEmployeeSurvey {

	static final Logger logger = LogManager
			.getLogger(EmployeeSurveyDaoProxy.class.getName());
	private IEmployeeSurvey iEmployeeSurvey;

	public IEmployeeSurvey getiEmployeeSurvey() {
		return iEmployeeSurvey;
	}

	public void setiEmployeeSurvey(IEmployeeSurvey iEmployeeSurvey) {
		this.iEmployeeSurvey = iEmployeeSurvey;
	}

	@Override
	public void addEmployeeSurvey(EmployeeSurvey empSurvey) {
		logger.info("In EmployeeSurveyDaoProxy");
		EmployeeSurveyDao.instance.addEmployeeSurvey(empSurvey);

	}

	@Override
	public Map<String, EmployeeSurvey> getAllSurveyConduced() {
		logger.info("In EmployeeSurveyDaoProxy");
		Map<String, EmployeeSurvey> employeeSurveys = EmployeeSurveyDao.instance
				.getAllSurveyConduced();
		return employeeSurveys;
	}

	@Override
	public List<Survey> getAllSurveyConducedByEmpId(String empId) {
		logger.info("In EmployeeSurveyDaoProxy");
		List<Survey> surveys = EmployeeSurveyDao.instance
				.getAllSurveyConducedByEmpId(empId);
		return surveys;
	}
}
