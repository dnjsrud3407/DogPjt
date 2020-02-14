package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.vo.ActionForward;

public class MemberLogOutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("MemberLogOutAction");
		
		// session 객체 가져오기
		HttpSession session = request.getSession();
		
		// session 객체 비우기
		session.invalidate();
		
		forward = new ActionForward();
		forward.setPath("");
		forward.setRedirect(true);
		
		return forward;
	}

}
