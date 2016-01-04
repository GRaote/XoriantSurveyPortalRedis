package com.xoriant.test;

//import redis.clients.jedis.Jedis;

public class TestRedis {
	// private static Jedis jedis = new Jedis("localhost");

	public static void main(String args[]) {
		// Employee employee = new Employee("e02", "Adarsh");
		// Map<String, String> employeeDetail = new HashMap<String, String>();
		// employeeDetail.put("empId", employee.getEmpId());
		// employeeDetail.put("ename", employee.getEname());
		// jedis.hmset(employee.getEmpId(), employeeDetail);
		// jedis.lpush("employee", employee.getEmpId());

		// Map<String, String> employeeMap = jedis.hgetAll("e02");
		// Employee employee = new Employee(employeeMap.get("empId"),
		// employeeMap.get("ename"));
		// System.out.println(employee);

		// List<Employee> empList = new ArrayList<Employee>();
		// Long employeeLength = jedis.llen("employee");
		// List<String> employeeList = jedis.lrange("employee", 0,
		// employeeLength);
		// for(String empKey : employeeList){
		// Map<String, String> employee = jedis.hgetAll(empKey);
		// empList.add(new Employee(employee.get("empId"),
		// employee.get("ename")));
		// }
		// System.out.println(empList);

		// Survey survey=new Survey("002","YOO?","Yes","No","Open");
		// Map<String, String> surveyDetail = new HashMap<String, String>();
		// surveyDetail.put("surveyId", survey.getSurveyId());
		// surveyDetail.put("title", survey.getTitle());
		// surveyDetail.put("choice1", survey.getChoice1());
		// surveyDetail.put("choice2", survey.getChoice2());
		// surveyDetail.put("status", survey.getStatus());
		// jedis.hmset(survey.getSurveyId(), surveyDetail);
		// jedis.lpush("surveys", survey.getSurveyId());

		// Map<String, String> surveyMap = jedis.hgetAll("001");
		// Survey survey = new Survey(surveyMap.get("surveyId"),
		// surveyMap.get("title"), surveyMap.get("choice1"),
		// surveyMap.get("choice2"), surveyMap.get("status"));
		// System.out.println(survey);

		// Long surveyLength = jedis.llen("surveys");
		// List<String> surveyList = jedis.lrange("surveys", 0, surveyLength);
		// Map<String, Survey> surveysList = new HashMap<String, Survey>();
		// for (String surveyKey : surveyList) {
		// Map<String, String> survey = jedis.hgetAll(surveyKey);
		// surveysList.put(surveyKey,
		// new Survey(survey.get("surveyId"), survey.get("title"),
		// survey.get("choice1"), survey.get("choice2"),
		// survey.get("status")));
		// System.out.println(surveysList);
		// }

		// EmployeeSurvey empSurvey=new EmployeeSurvey("e01001","e01" , "001",
		// "Hi??", "Yes");
		// Map<String, String> empSurveyDetail = new HashMap<String, String>();
		// empSurveyDetail.put("empSurveyId",empSurvey.getEmpSurveyId());
		// empSurveyDetail.put("empId", empSurvey.getEmpId());
		// empSurveyDetail.put("surveyId", empSurvey.getSurveyId());
		// empSurveyDetail.put("title", empSurvey.getTitle());
		// empSurveyDetail.put("finalChoice", empSurvey.getFinalChoice());
		// jedis.hmset(empSurvey.getEmpSurveyId(), empSurveyDetail);
		// jedis.lpush("employeesurvey", empSurvey.getEmpSurveyId());

		// Long empSurveyLength = jedis.llen("employeesurvey");
		// List<String> empSurveyList = jedis.lrange("employeesurvey", 0,
		// empSurveyLength);
		// Map<String, EmployeeSurvey> employeeSurveyList = new HashMap<String,
		// EmployeeSurvey>();
		// for (String empSurveyKey : empSurveyList) {
		// Map<String, String> empSurvey = jedis.hgetAll(empSurveyKey);
		// employeeSurveyList.put(
		// empSurveyKey,
		// new EmployeeSurvey(empSurvey.get("empSurveyId"), empSurvey
		// .get("empId"), empSurvey.get("surveyId"), empSurvey
		// .get("title"), empSurvey.get("finalChoice")));
		// }
		// System.out.println(employeeSurveyList);

		
//		Long empSurveyLength = jedis.llen("employeesurvey");
//		List<String> empSurveyList = jedis.lrange("employeesurvey", 0,
//				empSurveyLength);
//		Map<String, EmployeeSurvey> employeeSurveyList = new HashMap<String, EmployeeSurvey>();
//		for (String empSurveyKey : empSurveyList) {
//			Map<String, String> empSurvey = jedis.hgetAll(empSurveyKey);
//			employeeSurveyList.put(
//					empSurveyKey,
//					new EmployeeSurvey(empSurvey.get("empSurveyId"), empSurvey
//							.get("empId"), empSurvey.get("surveyId"), empSurvey
//							.get("title"), empSurvey.get("finalChoice")));
//		}
//		System.out.println(employeeSurveyList);
		
		// Map<String, EmployeeSurvey> employeeSurveyList =
		// EmployeeSurveyDao.instance
		// .getAllSurveyConduced();
		// List<EmployeeSurvey> employeeSurvey = new ArrayList<>(
		// employeeSurveyList.values());
		// List<Survey> surveys = new ArrayList<Survey>();
		// for (EmployeeSurvey emp : employeeSurvey) {
		// if (emp.getEmpId().equals("e01")) {
		// surveys.add(new Survey(emp.getSurveyId(), emp.getTitle(), emp
		// .getFinalChoice()));
		// }
		// }
		// System.out.println(surveys);
	}
}
