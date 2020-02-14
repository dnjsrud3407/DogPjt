package member.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.action.Action;
import member.svc.MemberJoinProService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class MemberJoinProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("MemberJoinProAction");
		
		// 파라미터 가져오기
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email"); 
		
		// MemberBean 객체에 넣기
		MemberBean member = new MemberBean(name, id, pass, email);
		
		MemberJoinProService memberJoinProService = new MemberJoinProService();
		boolean isjoinSuccess = memberJoinProService.joinMember(member);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(isjoinSuccess) {	// 회원가입 된 경우
			System.out.println("회원가입 성공!");
			
		} else {			// 회원가입 실패
			out.println("<script>");
			out.println("alert('회원가입 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
