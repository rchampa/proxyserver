package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.ProjectVO;

public class ProjectDAO {
	
	private Connection conn;
	
	public ProjectDAO(Connection conn){
		this.conn = conn;
	}
	
	public ProjectVO getProject(int id){
		
		try{
		
			String selectSQL = "SELECT * FROM projects WHERE project_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				int project_id = rs.getInt("project_id");
				String project_name = rs.getString("project_name");	
				String uri = rs.getString("uri");
				int user_id = rs.getInt("user_id");
				
				return new ProjectVO(project_id, project_name, uri, user_id);
			}
			return null;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<ProjectVO> getProjects(int user_id){
		
		try{
		
			List<ProjectVO> projects = new ArrayList<ProjectVO>();
			String selectSQL = "SELECT * FROM projects WHERE user_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, user_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				int project_id = rs.getInt("project_id");
				String project_name = rs.getString("project_name");	
				String uri = rs.getString("uri");
				
				ProjectVO vo = new ProjectVO(project_id, project_name, uri, user_id);
				projects.add(vo);
			}
			return projects;
		}
		catch(Exception e){
			return null;
		}
	}
	
	
	public boolean update(ProjectVO project){
		try{
			String updateTableSQL = "UPDATE projects SET project_name=?, uri=?  WHERE project_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(updateTableSQL);
			preparedStatement.setString(1, project.getProject_name());
			preparedStatement.setString(2, project.getUri());
			preparedStatement.setInt(3, project.getProject_id());
			// execute insert SQL stetement
			return (preparedStatement.executeUpdate()>0);
		}
		catch(Exception e){
			return false;
		}
	}
	
}
