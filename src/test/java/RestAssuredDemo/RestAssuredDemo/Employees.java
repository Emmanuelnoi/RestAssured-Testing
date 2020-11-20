package RestAssuredDemo.RestAssuredDemo;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Employees {

	@Test
	
	void testGetEmployee()
	{
		RestAssured.baseURI="http://employeesbackend-env-1.eba-eqm9m3bw.us-east-1.elasticbeanstalk.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		Response response = httprequest.request(Method.GET,"/employees");
		String res = response.getBody().asString();
		System.out.println("Response is " + res);
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	
	void testAddEmployee()
	{
		RestAssured.baseURI="http://employeesbackend-env-1.eba-eqm9m3bw.us-east-1.elasticbeanstalk.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		JSONObject reqparams = new JSONObject();
		reqparams.put("emp_id", 1);
		reqparams.put("emp_name", "Chris Brown");
		reqparams.put("emp_title", "CEO");
		httprequest.header("Content-Type","application/json");
		httprequest.body(reqparams.toJSONString());
		Response httpresponse = httprequest.request(Method.POST,"/employees");
		int statusCode = httpresponse.statusCode();
		String statusMessage = httpresponse.jsonPath().get("status");
		Assert.assertEquals(statusCode, 200);
		//Assert.assertEquals(statusMessage, "success");
	}
	
	@Test
	
	void updateEmployee()
	{
		int emp_id = 2;
		
		RestAssured.baseURI="http://employeesbackend-env-1.eba-eqm9m3bw.us-east-1.elasticbeanstalk.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		
		JSONObject reqparams = new JSONObject();
		reqparams.put("emp_name", "Martin King");
		reqparams.put("emp_title", "Vice President");
		
		httprequest.header("Content-Type","application/json");
		httprequest.body(reqparams.toJSONString());
		Response response = httprequest.put("/employees/"+ emp_id);
		
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}
	
	@Test
	
	void deleteEmployee()
	{
		int emp_id = 7;

		RestAssured.baseURI="http://employeesbackend-env-1.eba-eqm9m3bw.us-east-1.elasticbeanstalk.com/api/v1";
		RequestSpecification httprequest = RestAssured.given();
		
		httprequest.header("Content-Type","application/json");
		Response response = httprequest.delete("/employees/"+ emp_id);
		
		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
		
		//String statusMessage = response.jsonPath().get("status");
		//Assert.assertEquals(statusMessage, "success");
	}
}
