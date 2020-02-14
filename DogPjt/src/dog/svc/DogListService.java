package dog.svc;

import java.sql.Connection;
import java.util.ArrayList;
import static db.JdbcUtil.*;

import dog.dao.DogDAO;
import dog.vo.DogBean;

public class DogListService {

	public ArrayList<DogBean> getDogList() {
		ArrayList<DogBean> dogList = null;
//		System.out.println("DogListService");
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		// DogList 가져오기 
		dogList = dogDAO.selectDogList();
		
		
		
		close(con);
		
		return dogList;
	}
	
}
