package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.MockupVO;

public class MockupDAO {
	
	private Connection conn;
	
	public MockupDAO(Connection conn){
		this.conn = conn;
	}
	
	public boolean insert(MockupVO mockup){
		
		try{
			String insertTableSQL = "INSERT INTO mockups"
					+ "(url_mock, headers_mock, method_mock, data_mock, project_id) VALUES"
					+ "(?,?,?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, mockup.getUrl_mock());
			preparedStatement.setString(2, mockup.getHeaders_mock());
			preparedStatement.setString(3, mockup.getMethod_mock());
			preparedStatement.setString(4, mockup.getData_mock());
			preparedStatement.setInt(5, mockup.getProject_id());
			// execute insert SQL stetement
			return (preparedStatement.executeUpdate()>0);
		}
		catch(Exception e){
			return false;
		}
	}
	
	public MockupVO getMockup(int id){
		
		try{
		
			String selectSQL = "SELECT * FROM mockups WHERE mockup_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				int mockup_id = rs.getInt("mockup_id");
			    String url_mock = rs.getString("url_mock");
			    String headers_mock = rs.getString("headers_mock");
			    String method_mock = rs.getString("method_mock");
			    String data_mock = rs.getString("data_mock");
			    int project_id = rs.getInt("project_id");				
					

				return new MockupVO(mockup_id, url_mock, headers_mock, method_mock,data_mock, project_id);
			}
			return null;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public MockupVO getMockup(String url, String method){
		
		try{
		
			String selectSQL = "SELECT * FROM mockups WHERE url_mock=? and method_mock=?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setString(1, url);
			preparedStatement.setString(2, method);
			ResultSet rs = preparedStatement.executeQuery();
			MockupVO mockup=null;
			while (rs.next()) {
				
				int mockup_id = rs.getInt("mockup_id");
			    String url_mock = rs.getString("url_mock");
			    String headers_mock = rs.getString("headers_mock");
			    String method_mock = rs.getString("method_mock");
			    String data_mock = rs.getString("data_mock");
			    int project_id = rs.getInt("project_id");				
			    mockup = new MockupVO(mockup_id, url_mock, headers_mock, method_mock,data_mock, project_id);
			}
			return mockup;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<MockupVO> getMockups(int project_id){
		
		try{
		
			List<MockupVO> mockups = new ArrayList<MockupVO>();
			String selectSQL = "SELECT * FROM mockups WHERE project_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, project_id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				int mockup_id = rs.getInt("mockup_id");
			    String url_mock = rs.getString("url_mock");
			    String headers_mock = rs.getString("headers_mock");
			    String data_mock = rs.getString("data_mock");	
			    String method_mock = rs.getString("method_mock");
				
			    MockupVO vo = new MockupVO(mockup_id, url_mock, headers_mock, method_mock, data_mock, project_id);
			    mockups.add(vo);
			}
			return mockups;
		}
		catch(Exception e){
			return null;
		}
	}
	
	
}
