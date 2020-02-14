package dog.svc;

import java.sql.Connection;

import dog.dao.DogDAO;
import dog.vo.DogBean;
import static db.JdbcUtil.*;

public class DogRegistService {

	public boolean registDog(DogBean dogBean) {
		boolean isregistDogSuccess = false;
		
		DogDAO dogDAO = DogDAO.getInstance();
		Connection con = getConnection();
		dogDAO.setConnection(con);
		
		int insertCount = dogDAO.insertDog(dogBean);
		
		if(insertCount > 0) {
			commit(con);
			isregistDogSuccess = true;
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isregistDogSuccess;
	}

}
