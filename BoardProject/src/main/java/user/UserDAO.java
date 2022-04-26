package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 기본 생성자
	// UserDAO가 생성되면 자동으로 실행
	// 생성 시 마다 반복되는 코드 삽입
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost/BoardPractice";
			String dbId = "root";
			String dbPw = "kitejun921115";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbId, dbPw);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public int login(String userId, String userPw) {
		String sql = "select userPw from user where userId = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPw)) {
					System.out.println("로그인 성공!");
					return 1;
				}else {
					System.out.println("로그인 실패!");
					return 0;
				}
			}
			return -1;	// 아이디 없음
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -2;	// 오류
	}
	
	
	public int join(User user) {
		String sql = "insert into user values(?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPw());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	
}
