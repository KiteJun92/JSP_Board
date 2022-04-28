package com.jsp.board.post.model.service;

import java.sql.Connection;
import java.util.List;

import static com.jsp.board.common.JDBCTemplate.*; 
import com.jsp.board.post.model.dao.PostDAO;
import com.jsp.board.post.model.vo.Post;

public class PostService {

	private PostDAO dao = new PostDAO();
	

	/** 글 작성
	 * @param post
	 * @return result(성공 1 실패 0)
	 * @throws Exception
	 */
	public int write(Post post) throws Exception{
		
		Connection conn = getConnection();
		
		// 다음 게시글 번호 얻기 -> 게시글 + 이미지 업로드하는 경우 시퀀스가 꼬일 수 있음
//		int boardNo = dao.nextBoardNo(conn);
		
		
		// XSS 방지 처리 + 개행 문자 <br> 태그로 변경 (정규 표현식)
		
		int result = dao.write(post, conn);
		
		if(result > 0) {
			commit(conn);
//			result = post.getBoardNo();
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	/** 목록 조회하기
	 * @param page
	 * @return postList
	 * @throws Exception
	 */
	public List<Post> list(int page) throws Exception{
		
		Connection conn = getConnection();
		List<Post> postList = dao.list(page, conn);
		close(conn);
		return postList;
	}


	/** 상세 조회
	 * @param boardNo
	 * @return	post
	 * @throws Exception
	 */
	public Post detail(int boardNo) throws Exception{
		
		Connection conn = getConnection();
		Post post = dao.detail(boardNo, conn);
		close(conn);
		return post;
	}


	/** 비밀번호 체크(조회)
	 * @param boardNo
	 * @param pw
	 * @return result( 1 일치, 0 불일치 )
	 * @throws Exception
	 */
	public int checkPw(int boardNo, String pw) throws Exception{

		Connection conn = getConnection();
		int result = dao.checkPw(boardNo, pw, conn);
		close(conn);
		return result;
	}


	/** 게시글 삭제하기
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int delete(int boardNo) throws Exception{

		Connection conn = getConnection();
		int result = dao.delete(boardNo, conn);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}


	/** 게시글 수정
	 * @param post
	 * @return result
	 * @throws Exception
	 */
	public int update(Post post) throws Exception{
		Connection conn = getConnection();
		int result = dao.update(post, conn);
		if(result>0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}


	/** 조회수 증가
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateReadCount(int boardNo) throws Exception{
		Connection conn = getConnection();
		int result = dao.updateReadCount(boardNo, conn);
		if(result > 0)commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	

}
