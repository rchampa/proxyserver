package es.ric.proxyserver;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.dao.JDBCConnector;
import model.dao.MockupDAO;
import model.vo.MockupVO;

import com.opensymphony.xwork2.Action;


public class OldServerSetupAction implements Action {
	
	private Map<String, MyMockup> mockupsMap = new LinkedHashMap<String, MyMockup>();
	
	private int user_id;
	private int project_id;
	
	private final String SERVER_PATH;
	private final String SERVERNAME_PATH;
	private String actionType;
	private String servername;
	private String resource;
	private String mockup;
	private String method;
	
	public OldServerSetupAction(){
		super();
		SERVER_PATH = "/Users/Ricardo/Documents/proxy/";
		SERVERNAME_PATH = "/Users/Ricardo/Documents/proxy/servername.txt";
	}
	
	
	@Override
	public String execute() throws Exception {
		
		if(actionType.equals("load_mockups")){
			loadMockups();
		}
		else if(actionType.equals("update_mockup")){
			writeFile(servername, SERVERNAME_PATH);
		}
		else if(actionType.equals("delete_mockup")){
			
		}
		else if(actionType.equals("create_mockup")){
			
			if(method.equals("NONE")){
				return SUCCESS;
			}
			
			String directory = SERVER_PATH+method; 
			String path = directory+"/"+resource+".txt";
			writeFile(mockup, path);
			loadMockups();
		}
		
		return SUCCESS;
	}
	
	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getMockup() {
		return mockup;
	}

	public void setMockup(String mockup) {
		this.mockup = mockup;
	}
	public Map<String, MyMockup> getMockupsMap() {
		return mockupsMap;
	}

	public void setMockupsMap(Map<String, MyMockup> mockupsMap) {
		this.mockupsMap = mockupsMap;
	}

	public void loadMockups(){
		mockupsMap.clear();
		
		Connection conn = new JDBCConnector().getConnection();
		
		List<MockupVO> mockups = new MockupDAO(conn).getMockups(project_id);
		
		int num_id = 1;
		
		File folder_get = new File(SERVER_PATH+"GET");
		
		File[] listOfFiles = folder_get.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		String filename = listOfFiles[i].getName();
	    		if(!".DS_Store".equals(filename)){
	    			MyMockup myMockup = new MyMockup(filename.replace(".txt", ""), "GET");
		    		mockupsMap.put(""+num_id, myMockup);
		    		num_id++;
	    		}
	    		
	    	} 
	    }
	    
	    File folder_post = new File(SERVER_PATH+"POST");
		listOfFiles = folder_post.listFiles();
	    for (int i = 0; i < listOfFiles.length; i++) {
	    	if (listOfFiles[i].isFile()) {
	    		String filename = listOfFiles[i].getName();
	    		if(!".DS_Store".equals(filename)){
		    		MyMockup myMockup = new MyMockup(filename.replace(".txt", ""), "POST");
		    		mockupsMap.put(""+num_id, myMockup);
		    		num_id++;
	    		}
	    	} 
	    }
	}
	
	private void writeFile(String data, String path){
		PrintWriter writer;
		try {
			writer = new PrintWriter(path, "UTF-8");
			writer.println(data);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
