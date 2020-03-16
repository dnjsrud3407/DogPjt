package dog.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog.dao.DogDAO;
import dog.vo.CartBean;
import dog.vo.DogBean;

public class DogCartQtyChangeService {

	public void changeCartQty(int id, int qty, HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		// 세션 객체로부터 ArrayList<CartBean> 객체 가져오기
		ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("cartList");
		
		// for문을 사용하여 cartList 에서 id 값이 같은 상품 찾기
		for(CartBean cartBean : cartList) {
			if (cartBean.getId() == id) {
				// 수량 변경 작업
				cartBean.setQty(qty);
			}
		}
		// 별도로 cartBean을 cartList에 넣지 않아도
		// 주소값을 참조하기 때문에 값 적용이 된다
		// => 단 session값이 유효할 때 만이다
	}

}
