package dog.svc;

import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dog.dao.DogDAO;
import dog.vo.CartBean;
import dog.vo.DogBean;
import static db.JdbcUtil.*;

public class DogCartAddService {

	public DogBean getCartDog(int id) {
		DogBean dog = new DogBean();
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
//		System.out.println("DogCartAddService");
		dog = dogDAO.selectDog(id);
		
		close(con);

		return dog;
	}

	public void addCart(DogBean cartDog, HttpServletRequest request) {
		// Session에 저장하는 작업
		HttpSession session = request.getSession();
		
//		System.out.println("addCart");
		
		// 기존 세션에 저장되어 있던 카트 정보를 ArrayList<CartBean> 으로 저장
		ArrayList<CartBean> cartList = (ArrayList<CartBean>) session.getAttribute("cartList");
		
		// ArrayList<CartBean> 이 비어있을 경우 객체 생성 후 session 객체에 저장
		if (cartList == null) {
			cartList = new ArrayList<CartBean>();
			session.setAttribute("cartList", cartList);
		}
		
		// 장바구니에 중복 추가 방지하기
		boolean isNewCart = true;
		for(CartBean cartBean : cartList) {
			// DogBean 객체의 kind 값과 ArrayList 객체의 kind 값이 같은지 판별
			if(cartDog.getId() == cartBean.getId()) {
				// 기존 장바구니 상품과 중복!
				isNewCart = false;
				// => 수량증가!
				cartBean.setQty(cartBean.getQty() + 1);
				System.out.println("상품 중복됨 : " + cartDog.getId() + ", " + cartBean.getId());
				break;
			}
		}
		System.out.println("isNewCart : " + isNewCart);
		
		// 새 상품일 경우 cartList 에 cartBean 객체를 추가
		if(isNewCart) {
			CartBean cartBean = 
					new CartBean(cartDog.getId(), cartDog.getImage(), cartDog.getKind(), cartDog.getPrice(), 1);

			cartList.add(cartBean);		// cartList가 주소값을 참고하고 있기 때문에 add를 하면 session에 다 저장됨 
		}
	}

}






























