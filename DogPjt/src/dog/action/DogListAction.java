package dog.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogListService;
import dog.vo.ActionForward;
import dog.vo.DogBean;

public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ActionForward의 dogListAction()");
		ActionForward forward = null;
		
		
		// 오늘 본 상품 목록을 저장하는 ArrayList 객체 생성
		ArrayList<String> todayImageList = new ArrayList<String>();
		// 이전에 본 상품 목록이 저장되어 있는 쿠키에 접근하기 위해 request객체의 getCookies() 메서드 호출
		Cookie[] cookieArray = request.getCookies();
		// 쿠키가 비어있지 않은 경우 모든 쿠키 중 이름이 "today"로 시작하는 값을 todayImageList에 따로 저장
		if(cookieArray != null) {
			for (Cookie cookie : cookieArray) {
				if(cookie.getName().startsWith("today")) {
					// 디코딩 후 cookie 값으로 넣기 (사진파일명과 id값을 넣으면 링크걸기가 가능해진다!)
					todayImageList.add(URLDecoder.decode(cookie.getValue(), "UTF-8"));
//					System.out.println("디코딩 전 쿠키 : " + cookie.getValue());
					// 디코딩 후 쿠키
//					System.out.println("디코딩 후 쿠키 : " + URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}
			}
		}
		
		// DogList 저장되어 있는 총 목록 가져오기
		ArrayList<DogBean> dogList = new ArrayList<DogBean>();
		DogListService dogListService = new DogListService();
		dogList = dogListService.getDogList();
		
		
		// request 객체에 todayImageList와 dogList 객체 저장
		request.setAttribute("todayImageList", todayImageList);
		request.setAttribute("dogList", dogList);
		
		// dogList.jsp 페이지로 이동
		forward = new ActionForward();
//		System.out.println("dogList.jsp페이지로 이동");
		forward.setPath("./dog/dogList.jsp");		
		return forward;
	}

}
