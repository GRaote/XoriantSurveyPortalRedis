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

import com.xoriant.dao.EmployeeSurveyDao;
import com.xoriant.model.EmployeeSurvey;
import com.xoriant.model.Survey;
import com.xoriant.proxyimage.EmployeeSurveyDaoProxy;
import com.xoriant.proxyinterface.IEmployeeSurvey;

public class EmployeeSurveyResourceTest {
	private EmployeeSurveyDaoProxy empSurDaoProxy = null;
	private List<Survey> surList = null;
	private IEmployeeSurvey iEmployeeSurvey = null;
	private Map<String, EmployeeSurvey> empSurveys = new HashMap<String, EmployeeSurvey>();

	/**
	 * 
	 */
	@Before
	public void setup() {
		iEmployeeSurvey = mock(IEmployeeSurvey.class);
		surList = new ArrayList<Survey>();
		surList.add(new Survey("001"));
		surList.add(new Survey("050"));
		empSurDaoProxy = new EmployeeSurveyDaoProxy();
		empSurDaoProxy.setiEmployeeSurvey(iEmployeeSurvey);
		empSurveys.put("e01001", new EmployeeSurvey("e01001"));
		empSurveys.put("e01050", new EmployeeSurvey("e01050"));
	}

	@Test
	public void testGetAllSurveys() {
		boolean gotException = true;

		try {
			EmployeeSurveyDao.instance.getAllSurveyConduced();
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
	public void getAllEmployeeSurveysSuccessTest() {

		Mockito.when(iEmployeeSurvey.getAllSurveyConduced()).thenReturn(
				empSurveys);
		Map<String, EmployeeSurvey> empSurveys = null;
		empSurveys = empSurDaoProxy.getAllSurveyConduced();

		EmployeeSurvey empSur = empSurveys.get("e01001");
		EmployeeSurvey empSur1 = iEmployeeSurvey.getAllSurveyConduced().get(
				"e01001");
		assertEquals(empSur, empSur1);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getAllSurveysFailTest() {

		Mockito.when(iEmployeeSurvey.getAllSurveyConduced()).thenReturn(
				empSurveys);
		Map<String, EmployeeSurvey> empSurveys = null;
		empSurveys = empSurDaoProxy.getAllSurveyConduced();
		EmployeeSurvey empSur = empSurveys.get("e01001");
		EmployeeSurvey emSur1 = iEmployeeSurvey.getAllSurveyConduced().get(
				"050");

		boolean flag = empSur.equals(emSur1);
		assertFalse(flag);
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getSurveyByIdSuccessTest() {

		Mockito.when(iEmployeeSurvey.getAllSurveyConducedByEmpId("e01"))
				.thenReturn(surList);
		List<Survey> surList = new ArrayList<Survey>();
		surList = empSurDaoProxy.getAllSurveyConducedByEmpId("e01");
		List<Survey> surList1 = iEmployeeSurvey
				.getAllSurveyConducedByEmpId("e01");
		assertEquals(surList.get(0), surList1.get(0));
	}

	/**
	 * 
	 * 
	 */
	@Test
	public void getSurveyByIdFailTest() {

		Mockito.when(iEmployeeSurvey.getAllSurveyConducedByEmpId("e01"))
				.thenReturn(surList);
		List<Survey> surList = new ArrayList<Survey>();
		surList = empSurDaoProxy.getAllSurveyConducedByEmpId("e01");
		List<Survey> surList1 = iEmployeeSurvey
				.getAllSurveyConducedByEmpId("e01");
		assertEquals(surList.get(0), surList1.get(0));
		boolean flag = surList.get(0).equals(surList1.get(1));
		assertFalse(flag);
	}

}
