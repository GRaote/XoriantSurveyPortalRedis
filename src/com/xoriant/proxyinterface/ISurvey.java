package com.xoriant.proxyinterface;

import java.util.Map;

import com.xoriant.model.Survey;

public interface ISurvey {
	public void addSurvey(Survey survey);

	public Survey getSurvey(String surveyId);

	public Map<String, Survey> getAllSurvey();
}
