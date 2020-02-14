package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.svc.MemberLoginProService;
import member.vo.ActionForward;
import member.vo.MemberBean;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 파라미터 값 가져오기 
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		MemberBean memberBean = new MemberBean(id, pass);
		
		MemberLoginProService loginProService = new MemberLoginProService();
		
		// 로그인 작업 요청
		int loginResult = loginProService.isLoginMember(memberBean);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(loginResult == 1) {				// 일치하는 경우
			// session 에 id정보 저장
			// => java에서는 session 내장객체가 없으므로 HttpSession 인터페이스 사용해야함
			HttpSession session = request.getSession();
			session.setAttribute("sid", id);
			
			forward = new ActionForward();
			forward.setPath("");
			forward.setRedirect(true);
		} else if(loginResult == 0) {			// 아이디 없는 경우
			out.println("<script>");
			out.println("alert('아이디 없음')");
			out.println("history.back()");
			out.println("</script>");
		} else if(loginResult == -1) {		// 패스워드 틀린 경우
			out.println("<script>");
			out.println("alert('패스워드 틀림')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return forward;
	}

}
