package dog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dog.action.Action;
import dog.action.DogCartAddAction;
import dog.action.DogCartListAction;
import dog.action.DogCartQtyChangeAction;
import dog.action.DogCartRemoveAction;
import dog.action.DogRegistAction;
import dog.action.DogViewAction;
import dog.action.DogListAction;
import dog.vo.ActionForward;

@WebServlet("*.dog")
public class DogFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String command = request.getServletPath();
		System.out.println("command : " + command);
		
		Action action = null;
		ActionForward forward = null;
		
		// 서블릿 주소에 따라 각각 다른 작업을 수행
		if(command.equals("/DogList.dog")) {
			// --- 강아지 목록 dogListAction()
			action = new DogListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if(command.equals("/DogRegistForm.dog")) {
			// --- 강아지 등록 폼 View
			forward = new ActionForward();
			forward.setPath("./dog/dogRegistForm.jsp");
		} else if(command.equals("/DogRegist.dog")) {
			// ------------------------------------ 강아지 등록 작업 DogRegistAction()
			action = new DogRegistAction();
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/DogView.dog")) {
			// --- 강아지 상세보기 DogViewAction()
			action = new DogViewAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/DogCartAdd.dog")) {
			// --- 장바구니 담기 DogCartAddAction()
			action = new DogCartAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/DogCartList.dog")) {
			// --- 장바구니 목록 DogCartListAction();
			action = new DogCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/DogCartQtyChange.dog")) {
			// --- 수량 변경 DogCartQtyChangeAction() 
			action = new DogCartQtyChangeAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/DogCartRemove.dog")) {
			// --- 삭제하기 DogCartRemoveAction()
			action = new DogCartRemoveAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		
		// forward 
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		} else {
			System.out.println("ActionForward 값이 null입니다");
		}
		 
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
