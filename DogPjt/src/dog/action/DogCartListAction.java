package dog.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.svc.DogCartListService;
import dog.svc.DogListService;
import dog.vo.ActionForward;
import dog.vo.CartBean;

public class DogCartListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
//		System.out.println("DogCartListAction");
		
		// DogCartListService 의 getCartList() 메서드를 호출하여 카드 목록 정보 가져오기
		DogCartListService dogCartListService = new DogCartListService();
		ArrayList<CartBean> cartList = dogCartListService.getCartList(request);
		
//		for(CartBean cartBean : cartList) {
//			System.out.println(cartBean.getId() + ", " + cartBean.getKind() + ", " + cartBean.getQty());
//		}
		
		// 상품 금액 계산
		int totalMoney = 0;
		int money = 0;
		
		// 장바구니 항목 각각의 금액 계산 후 각 금액을 전체 합계 금액에 누정
		for(CartBean cartBean : cartList) {
			money = cartBean.getPrice() * cartBean.getQty();
			totalMoney += money;
		}
		
		// request 객체에 cartList 와 총 합계 금액을 저장
		request.setAttribute("cartList", cartList);
		request.setAttribute("totalMoney", totalMoney);
		
		// dogCartList.jsp 페이지로 이동
		forward = new ActionForward();
		forward.setPath("./dog/dogCartList.jsp");
		
		return forward;
	}

}
