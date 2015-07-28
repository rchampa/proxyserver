package es.ric.proxyserver;

import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.dao.JDBCConnector;
import model.dao.MockupDAO;
import model.dao.ProjectDAO;
import model.vo.MockupVO;
import model.vo.ProjectVO;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


//public class ServerSetupAction implements Action {
public class ServerSetupAction extends ActionSupport implements SessionAware{
	
	private Map<String, Object> session;
	
	private Map<String, MockupVO> mockupsMap = new LinkedHashMap<String, MockupVO>();
	
	private int project_id;
	private String actionType;
	private String servername;
	private String resource;
	private String mockup;
	private String method;
	
	@Override
	public String execute() throws Exception {
		
		if(actionType.equals("load_mockups")){
			JDBCConnector jdbc = new JDBCConnector();
				Connection conn = jdbc.getConnection();
				ProjectVO project = (ProjectVO) session.get("project");
				project = new ProjectDAO(conn).getProject(project_id);//project_id sólo es válido en "load_mockups"
				session.put("project", project);
			jdbc.close();
			loadMockups();
		}
		else if(actionType.equals("update_mockup")){
			ProjectVO project = (ProjectVO) session.get("project");
			project.setUri(servername);
			Connection conn = new JDBCConnector().getConnection();
			boolean ok = new ProjectDAO(conn).update(project);
		}
		else if(actionType.equals("delete_mockup")){
			
		}
		else if(actionType.equals("create_mockup")){
			
			if(method.equals("NONE")){
				return SUCCESS;
			}
			ProjectVO project = (ProjectVO) session.get("project");
			MockupVO mockupvo = new MockupVO(resource, "", method, mockup, project.getProject_id());
			
			Connection conn = new JDBCConnector().getConnection();
			boolean ok = new MockupDAO(conn).insert(mockupvo);
			
			loadMockups();
		}
		
		return SUCCESS;
	}
	
	
	private void loadMockups(){
		mockupsMap.clear();
		
		Connection conn = new JDBCConnector().getConnection();
		ProjectVO project = (ProjectVO) session.get("project");
		
		List<MockupVO> mockups = new MockupDAO(conn).getMockups(project.getProject_id());
		
		for (MockupVO mockupVO : mockups) {
			mockupsMap.put(""+mockupVO.getMockup_id(), mockupVO);
		}
		
	}
	
	
	
	//GETTERS and SETTERS
	
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
	public Map<String, MockupVO> getMockupsMap() {
		return mockupsMap;
	}
	public void setMockupsMap(Map<String, MockupVO> mockupsMap) {
		this.mockupsMap = mockupsMap;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}

	
}
