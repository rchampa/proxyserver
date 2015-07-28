package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.vo.UserVO;

public class UserDAO {
	
	private Connection conn;
	
	public UserDAO(Connection conn){
		this.conn = conn;
	}
	
	public UserVO getUser(int id){
		
		try{
		
			String selectSQL = "SELECT * FROM users WHERE user_id = ?";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int userid = rs.getInt("user_id");
				String username = rs.getString("username");	
				return new UserVO(userid, username);
			}
			return null;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public List<UserVO> getUsers(){
		
		try{
		
			List<UserVO> users = new ArrayList<UserVO>();
			String selectSQL = "SELECT * FROM users";
			PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int userid = rs.getInt("user_id");
				String username = rs.getString("username");	
				UserVO vo = new UserVO(userid, username);
				users.add(vo);
			}
			return users;
		}
		catch(Exception e){
			return null;
		}
	}
	
	
}
