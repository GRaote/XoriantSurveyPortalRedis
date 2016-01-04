package com.xoriant.proxyinterface;

import java.util.List;
import java.util.Map;

import com.xoriant.model.EmployeeSurvey;
import com.xoriant.model.Survey;

public interface IEmployeeSurvey {
	public void addEmployeeSurvey(EmployeeSurvey empSurvey);

	public Map<String, EmployeeSurvey> getAllSurveyConduced();

	public List<Survey> getAllSurveyConducedByEmpId(String empId);
}
