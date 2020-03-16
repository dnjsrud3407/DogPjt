package dog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartRemoveService;
import dog.vo.ActionForward;

public class DogCartRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("DogCartRemoveAction");
		ActionForward forward = null;
		
		// 체크박스(remove) 값 가져오기
		String[] cartIdArr = request.getParameterValues("remove");
		
		DogCartRemoveService dogCartRemoveService = new DogCartRemoveService();
		dogCartRemoveService.cartRemove(cartIdArr, request);
		
		forward = new ActionForward();
		forward.setPath("DogCartList.dog");
		forward.setRedirect(true);
		
		return forward;
	}

}
