package com.jsp.board.post.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.post.model.service.PostService;
import com.jsp.board.post.model.vo.Post;


@WebServlet("/*")
public class PostController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 전송 방식
		String method = req.getMethod();
		
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String command = uri.substring((contextPath + "/").length());
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		// 게시글 조회, 상세조회, 등록, 수정, 삭제
		try {
			
			PostService service = new PostService();
			
			// 목록 조회
			if(command.equals("list")) {
				
				
			}
			// 상세 조회
			else if(command.equals("detail")) {
				
				
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
					
					// write.jsp 에서 parameter 받아와서 post에 담기 
					
					
					
					int result = service.write(post);
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
