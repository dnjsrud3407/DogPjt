package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartAddService;
import dog.svc.DogCartQtyChangeService;
import dog.vo.ActionForward;
import dog.vo.DogBean;

public class DogCartQtyChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("DogCartQtyChangeAction");
		ActionForward forward = null;
		
		// 파라미터 값 가져오기
		int id = Integer.parseInt(request.getParameter("id"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.println("id : " + id + ", qty : " + qty);
		
		// DogCartQtyChangeService 의 getCartDog() 메서드를 호출하여 id에 해당하는 개 정보 가져오기
		DogCartQtyChangeService dogCartQtyChangeService = new DogCartQtyChangeService();
		dogCartQtyChangeService.changeCartQty(id, qty, request);
		
		forward = new ActionForward();
		forward.setPath("DogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
