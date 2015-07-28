package model.vo;

public class UserVO {

	private int user_id;
    private String username;
    
	public UserVO(int user_id, String username) {
		this.user_id = user_id;
		this.username = username;
	}

	public int getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}
    
}
