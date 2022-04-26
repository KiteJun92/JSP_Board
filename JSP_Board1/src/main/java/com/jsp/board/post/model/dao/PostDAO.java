package com.jsp.board.post.model.dao;

import static com.jsp.board.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import com.jsp.board.post.model.vo.Post;

// DAO (Data Access Object)
public class PostDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	// Properties : key, value가 모두 String인 Map
	
	public PostDAO() {
		// DAO 객체 생성 시 특정 위치의 XML 파일을 읽어오고 prop 객체에 저장
		try {
			prop = new Properties();
			
			String filePath = PostDAO.class.getResource("/com/jsp/board/sql/board-query.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/** 글 작성
	 * @param post
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int write(Post post, Connection conn) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("write");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, post.getBoardTitle());
			pstmt.setString(2, post.getBoardContent());
			pstmt.setString(3, post.getBoardWriter());
			pstmt.setString(4, post.getBoardPw());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
}
