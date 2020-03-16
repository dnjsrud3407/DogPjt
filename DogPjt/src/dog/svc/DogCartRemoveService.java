package dog.svc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog.vo.CartBean;

public class DogCartRemoveService {

	public void cartRemove(String[] cartIdArr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("cartList");
		
		// 향상된 for문을 사용할 때 index를 자동으로 가져오게 되는데
		// remove를 index가 하나씩 당겨지기 때문에 
		// remove를 할 때는 향상된 for문을 사용하지 않는다!
//		for(CartBean cartBean : cartList) {
//			for(String id : cartIdArr) {
//				if(cartBean.getId() == Integer.parseInt(id)) {
					
//					cartList.remove(cartBean);
//				}
//			}
//		}
		for (int i = 0; i < cartList.size(); i++) {
			for (int j = 0; j < cartIdArr.length; j++) {
				if (cartList.get(i).getId() == Integer.parseInt(cartIdArr[j])) {
					//cartList.remove(cartList.get(i));	// 객체(Object)로 제거할 때
					cartList.remove(i);	// 인덱스(int)로 제거할 때
				}
			}
		}
		
	}

}
