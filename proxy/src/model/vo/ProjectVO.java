package model.vo;

public class ProjectVO {
	
	private int project_id;
	private String project_name;
	private String uri;
	private int user_id;
	
	public ProjectVO(int project_id, String project_name, String uri, int user_id) {
		this.project_id = project_id;
		this.project_name = project_name;
		this.uri = uri;
		this.user_id = user_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public String getUri() {
		return uri;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	

}
