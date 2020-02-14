package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartAddService;
import dog.vo.ActionForward;
import dog.vo.DogBean;

public class DogCartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		// 파라미터 값 가져오기
		int id = Integer.parseInt(request.getParameter("id"));
		
		// DogCartAddService 의 getCartDog() 메서드를 호출하여 id에 해당하는 개 정보 가져오기
		DogCartAddService dogCartAddService = new DogCartAddService();
		DogBean cartDog = dogCartAddService.getCartDog(id);
		
		// DogCartAddService 의 addCart() 메서드를 호출하여 장바구니 담기 작업 수행
		// Service 클래스에는 request 객체가 없기 때문에 파라미터로 넘겨줘야한다
		dogCartAddService.addCart(cartDog, request);
		
		
		// ActionForward 객체를 사용하여 DogCartList.dog 페이지로 이동 설정
		forward = new ActionForward();
		forward.setPath("DogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
