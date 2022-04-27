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
					resp.sendRedirect(req.getContextPath() + "/board/write");
					
				}
				
				
			}
			// 수정
			else if(command.equals("update")) {
				
				
				
			}
			// 삭제
			else if(command.equals("delete")) {
				
				
				
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
