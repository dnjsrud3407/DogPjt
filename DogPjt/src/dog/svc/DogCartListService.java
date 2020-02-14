package dog.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import dog.vo.CartBean;

public class DogCartListService {

	public ArrayList<CartBean> getCartList(HttpServletRequest request) {
		
		// HttpSession 객체 가져와서 세션 내의 ArrayList<CartBean> 객체 가져오기
		ArrayList<CartBean> cartList 
			= (ArrayList<CartBean>) request.getSession().getAttribute("cartList");
		
		return cartList;
	}
	
}
