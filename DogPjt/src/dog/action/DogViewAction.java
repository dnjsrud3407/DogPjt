package dog.action;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogViewService;
import dog.vo.ActionForward;
import dog.vo.DogBean;

public class DogViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("DogViewAction");
		ActionForward forward = null;
		
		// 파라미터 id 값 가져오기
		int id = Integer.parseInt(request.getParameter("id"));
		
		// DogViewService 에서 id 값으로 DogBean 객체 가져오기
		DogViewService dogViewService = new DogViewService();
		DogBean dogBean = dogViewService.getDogView(id);
		
		// dogBean 객체가 null 이 아니면 조회수 1증가
		if(dogBean != null) {
			dogViewService.plusReadcount(id);
		}
		
		// request 에 dogBean 객체 담기
		request.setAttribute("dog", dogBean);
		
		// 현재 상세정보를 읽어온 상품의 id 값을 이름으로 하여 이미지 파일명을 쿠키에 추가(쿠키 유효기간 1일)
		//										  쿠키값을 한글로 지정할 때 발생하는 오류 해결책!
		Cookie cookie = new Cookie("today" + id, URLEncoder.encode(dogBean.getImage(), "UTF-8"));
		cookie.setMaxAge(60*60*24);
		// response 객체에 쿠키 전달
		response.addCookie(cookie);
		
		// dogView.jsp 페이지로 이동
		forward = new ActionForward();
		forward.setPath("./dog/dogView.jsp");
		
		return forward;
	}

}
