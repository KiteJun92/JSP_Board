package com.jsp.board.post.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.board.post.model.service.PostService;
import com.jsp.board.post.model.vo.Post;


@WebServlet("/board/*")
public class PostController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		// 데이터 전송 방식
		String method = req.getMethod();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/board/").length());
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		// 게시글 조회, 상세조회, 등록, 수정, 삭제
		try {
			
			PostService service = new PostService();
			
			// 메인
			if(command.equals("main")) {
				req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
			}
			// 목록 조회
			else if(command.equals("list")) {
				
				
				List<Post> postList = new ArrayList<Post>(); 
				
				// 파라미터로 넘어온 페이지가 비어있는 경우 1 , 아니면 페이지
//				int page = (!req.getParameter("page").isEmpty() ? Integer.parseInt(req.getParameter("page")) : 1);
				int page = 1;
				
				postList = service.list(page);
				
				req.setAttribute("postList", postList);
				
				req.getRequestDispatcher("/WEB-INF/views/list.jsp").forward(req, resp);
			}
			// 상세 조회
			else if(command.equals("detail")) {
				
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				Post post = service.detail(boardNo);
				
				
				req.setAttribute("post", post);
				req.getRequestDispatcher("/WEB-INF/views/detail.jsp").forward(req, resp);
			}
			// 등록
			else if(command.equals("write")) {
				
				if(method.equals("GET")) {
					path = "/WEB-INF/views/write.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
				}
				// POST
				else {
					
					Post post = new Post();
					HttpSession session = req.getSession();
					
					// write.jsp 에서 parameter 받아와서 post에 담기 
					post.setBoardTitle(req.getParameter("boardTitle"));
					post.setBoardWriter(req.getParameter("boardWriter"));
					post.setBoardPw(req.getParameter("boardPw"));
					post.setBoardContent(req.getParameter("boardContent"));
					
					int result = service.write(post);
					
					if(result > 0) {
						message = "등록 성공!";
						System.out.println("등록 성공");
					}else {
						message = "등록 실패!";
						System.out.println("실패");
					}
					
					session.setAttribute("message",message);
					resp.sendRedirect(req.getContextPath() + "/board/list");
					
				}
				
				
			}
			// 수정 페이지 이동
			else if(command.equals("updatePage")) {
				int boardNo = Integer.parseInt(req.getParameter("no"));
				String pw = req.getParameter("boardPw");
				HttpSession session = req.getSession();
				
				int result = service.checkPw(boardNo, pw);
				if(result > 0) {
					Post post = service.detail(boardNo);
					req.setAttribute("post", post);
					path="/WEB-INF/views/update.jsp";
					req.getRequestDispatcher(path).forward(req, resp);
				}else {
					message = "비밀번호가 일치하지 않습니다.";
					path = req.getContextPath() + "/board/detail?no=" + boardNo;
					session.setAttribute("message", message);
					resp.sendRedirect(path);
				}
			}
			// 수정 실행
			else if(command.equals("update")) {
				
				Post post = new Post();
				HttpSession session = req.getSession();
				
				// update.jsp 에서 parameter 받아와서 post에 담기 
				post.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
				post.setBoardTitle(req.getParameter("boardTitle"));
				post.setBoardContent(req.getParameter("boardContent"));
				
				int result = service.update(post);
				
				if(result > 0) {
					message = "수정 성공!";
				}else {
					message = "수정 실패!";
				}
				
				session.setAttribute("message",message);
				resp.sendRedirect(req.getContextPath() + "/board/list");
				
				
				
			}
			// 삭제
			else if(command.equals("delete")) {
				
				// 비밀번호 검사 메서드(service에서 수행) , 글 번호 + 비밀번호
				int boardNo = Integer.parseInt(req.getParameter("no"));
				String pw = req.getParameter("boardPw");
				HttpSession session = req.getSession();
				
				int result = service.checkPw(boardNo, pw);
				
				if(result > 0) {
					result = service.delete(boardNo);
					if(result > 0) {
						// 삭제 성공
						message = "삭제되었습니다.";
					}else {
						message = "삭제 실패";
					}
					path = req.getContextPath() + "/board/list";
				}else {
					// 비밀번호가 틀렸습니다.
					message = "비밀번호가 틀렸습니다.";
					path = req.getContextPath() + "/board/detail?no=" + boardNo; 
				}
				
				if(message != null) session.setAttribute("message", message);
				resp.sendRedirect(path);
				
			}
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
