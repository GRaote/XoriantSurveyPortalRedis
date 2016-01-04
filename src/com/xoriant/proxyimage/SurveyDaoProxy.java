package com.xoriant.proxyimage;

import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.xoriant.dao.SurveyDao;
import com.xoriant.model.Survey;
import com.xoriant.proxyinterface.ISurvey;

public class SurveyDaoProxy implements ISurvey {
	static final Logger logger = LogManager.getLogger(SurveyDaoProxy.class
			.getName());
	private ISurvey iSurvey;

	public ISurvey getiSurvey() {
		return iSurvey;
	}

	public void setiSurvey(ISurvey iSurvey) {
		this.iSurvey = iSurvey;
	}

	@Override
	public void addSurvey(Survey survey) {
		logger.info("In SurveyDaoProxy");
		SurveyDao.instance.addSurvey(survey);

	}

	@Override
	public Survey getSurvey(String surveyId) {
		logger.info("In SurveyDaoProxy");
		Survey survey = SurveyDao.instance.getSurvey(surveyId);
		return survey;
	}

	@Override
	public Map<String, Survey> getAllSurvey() {
		logger.info("In SurveyDaoProxy");
		Map<String, Survey> surveys = SurveyDao.instance.getAllSurvey();
		return surveys;
	}

}
