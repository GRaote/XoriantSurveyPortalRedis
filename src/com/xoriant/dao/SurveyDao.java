package com.xoriant.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

import com.xoriant.model.Survey;
import com.xoriant.proxyinterface.ISurvey;

/**
 * 
 * @author raote_g
 * 
 */
public enum SurveyDao implements ISurvey {
	instance;
	private Jedis jedis = new Jedis("localhost");

	/**
	 * Adds survey
	 * 
	 * @param survey
	 */
	@Override
	public void addSurvey(Survey survey) {
		Map<String, String> surveyDetail = new HashMap<String, String>();
		surveyDetail.put("surveyId", survey.getSurveyId());
		surveyDetail.put("title", survey.getTitle());
		surveyDetail.put("choice1", survey.getChoice1());
		surveyDetail.put("choice2", survey.getChoice2());
		surveyDetail.put("status", survey.getStatus());
		jedis.hmset(survey.getSurveyId(), surveyDetail);
		jedis.lpush("surveys", survey.getSurveyId());

	}

	/**
	 * Returns survey by id
	 * 
	 * @param surveyId
	 * @return
	 */
	@Override
	public Survey getSurvey(String surveyId) {

		Map<String, String> surveyMap = jedis.hgetAll(surveyId);
		Survey survey = new Survey(surveyMap.get("surveyId"),
				surveyMap.get("title"), surveyMap.get("choice1"),
				surveyMap.get("choice2"), surveyMap.get("status"));
		return survey;
	}

	/**
	 * Returns the list of Surveys
	 * 
	 * @return
	 */
	@Override
	public Map<String, Survey> getAllSurvey() {

		Long surveyLength = jedis.llen("surveys");
		List<String> surveyList = jedis.lrange("surveys", 0, surveyLength);
		Map<String, Survey> surveysList = new HashMap<String, Survey>();
		for (String surveyKey : surveyList) {
			Map<String, String> survey = jedis.hgetAll(surveyKey);
			surveysList.put(surveyKey,
					new Survey(survey.get("surveyId"), survey.get("title"),
							survey.get("choice1"), survey.get("choice2"),
							survey.get("status")));
		}

		return surveysList;
	}

}