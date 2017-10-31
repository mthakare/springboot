package org.spring.app.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;







  

@RestController
public class AppController {

	
	private Connection c = null;
	@Value("${spring_app.db_url}")
	private String dbConnectionUrl; 

	@Value("${spring_app.db_user}")
	private String dbUser; 

	@Value("${spring_app.db_password}")
	private String dbPassword; 

	private synchronized Connection getConnection() throws SQLException, ClassNotFoundException {
		if (c == null) {
			
			
			
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection(dbConnectionUrl, dbUser, dbPassword);
		}
		
		return c;
	}

	
	@RequestMapping("/v1/springapp/version")		
    public Map getVersion() {
		
		
		
		Map<String, String> versionMap = new HashMap<String, String>();
		
		versionMap.put("version", "1.0.0");
		versionMap.put("name", "vertx app service");
		versionMap.put("copyright", "BMC Software, 2017");
		versionMap.put("copyright", "Apahce 2.0 License");
		
        return versionMap;
    }
	
	@RequestMapping("/v1/springapp/records")		
    public ArrayList<Map<String, String>> getWorkflows() {
		
		
	    ArrayList<Map<String, String>> workflowList = new ArrayList<Map<String, String>>();
		 	
      try {
	         
	         ResultSet rs = getConnection().createStatement().executeQuery("select id, name, owner, policy, service_id FROM master_schema.project");
	   	
	         while (rs.next()) {
	        	 Map<String, String> workflowMap = new HashMap<String, String>();
	     		
	        	 String id = rs.getString(1);
	        	 String name = rs.getString(2);
	        	 String owner = rs.getString(3);
	        	 String patching_policy = (new JSONObject(rs.getString(4))).toString();
	        	 String service_id = rs.getString(5);
	        	 
	        	 workflowMap.put("id",id);
	        	 workflowMap.put("name",name);
	        	 workflowMap.put("owner",owner);
	        	 workflowMap.put("policy",patching_policy);
	        	 workflowMap.put("service_id",service_id);
	        	 workflowList.add(workflowMap);
	        }
	         
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println(e.getClass().getName()+": "+e.getMessage());
      }
     
      return workflowList;
   }
}
