package dog.svc;

import java.sql.Connection;
import static db.JdbcUtil.*;

import dog.dao.DogDAO;
import dog.vo.DogBean;

public class DogViewService {

	public DogBean getDogView(int id) {
		DogBean dogBean = null;
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		dogBean = dogDAO.selectDog(id);
		
		
		close(con);
		
		return dogBean;
	}

	public void plusReadcount(int id) {
		Connection con = getConnection();
		DogDAO dogDAO = DogDAO.getInstance();
		dogDAO.setConnection(con);
		
		int updateCount = dogDAO.updateReadcount(id);
		
		if(updateCount > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
	}

}
