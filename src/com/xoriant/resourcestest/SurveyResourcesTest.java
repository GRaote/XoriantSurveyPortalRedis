package com.xoriant.resourcestest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.xoriant.dao.SurveyDao;
import com.xoriant.model.Survey;
import com.xoriant.proxyimage.SurveyDaoProxy;
import com.xoriant.proxyinterface.ISurvey;

public class SurveyResourcesTest {

	private SurveyDaoProxy surDaoProxy = null;
	private List<Survey> surList = null;
	private ISurvey iSurvey = null;
	private Map<String, Survey> surveys = new HashMap<String, Survey>();

	/**
	 * 
	 */
	@Before
	public void setup() {
		iSurvey = mock(ISurvey.class);
		surList = new ArrayList<Survey>();
		surList.add(new Survey("001"));
		surList.add(new Survey("050"));
		surDaoProxy = new SurveyDaoProxy();
		surDaoProxy.setiSurvey(iSurvey);
		surveys.put("001", new Survey("001"));
		surveys.put("050", new Survey("050"));
	}

	/**
	 * 
	 */
	@Test
	public void testGetAllSurveys() {
		boolean gotException = true;

		try {
			SurveyDao.instance.getAllSurvey();
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
	public void getAllSurveysSuccessTest() {

		Mockito.when(iSurvey.getAllSurvey()).thenReturn(surveys);
		Map<String, Survey> surveys = null;
		surveys = surDaoProxy.getAllSurvey();
		Survey sur = surveys.get("001");
		Survey sur1 = iSurvey.getAllSurvey().get("001");
		assertEquals(sur, sur1);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getAllSurveysFailTest() {


		Mockito.when(iSurvey.getAllSurvey()).thenReturn(surveys);
		Map<String, Survey> surveys = null;
		surveys = surDaoProxy.getAllSurvey();
		Survey sur = surveys.get("001");
		Survey sur1 = iSurvey.getAllSurvey().get("050");

		boolean flag = sur.equals(sur1);
		assertFalse(flag);
	}
	
	/**
	 * 
	 * 
	 */
	@Test
	public void getSurveyByIdSuccessTest() {

		Mockito.when(iSurvey.getSurvey("001")).thenReturn(surveys.get("001"));
		Survey sur=new Survey();
		sur = surDaoProxy.getSurvey("001");
		Survey sur1 = iSurvey.getSurvey("001");
		assertEquals(sur, sur1);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getSurveyByIdFailTest() {


		Mockito.when(iSurvey.getSurvey("050")).thenReturn(surveys.get("050"));
		Survey sur=new Survey();
		sur = surDaoProxy.getSurvey("001");
		Survey sur1 = iSurvey.getSurvey("050");
		boolean flag = sur.equals(sur1);
		assertFalse(flag);
	}

}
