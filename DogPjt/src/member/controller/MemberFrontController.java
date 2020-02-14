package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.Action;
import member.action.MemberLogOutAction;
import member.action.MemberLoginProAction;
import member.vo.ActionForward;

@WebServlet("*.me")
public class MemberFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 서블릿 주소에 따라 각각 다른 작업을 수행
		if(command.equalsIgnoreCase("/MemberLoginForm.me")) {
			// --- 로그인 폼 View
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
		} else if(command.equalsIgnoreCase("/MemberLoginPro.me")) {
			// --- 로그인 확인 작업 MemberLoginProAction() 필요
			action = new MemberLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberJoinForm.me")) {
			// --- 회원가입 폼 View
			forward = new ActionForward();
			forward.setPath("./member/joinForm.jsp");
		} else if(command.equals("/MemberJoinPro.me")) {
			// --- 회원가입 작업 MemberJoinProAction() 필요
			action = new MemberJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberLogOut.me")) {
			// --- 로그아웃 작업 MemberLoginOutAction() 필요
			action = new MemberLogOutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		// forward 
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("ActionForward 값이 null입니다");
		}
		
		
		
		
		
		
		
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
