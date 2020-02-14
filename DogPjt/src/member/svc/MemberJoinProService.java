package member.svc;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static db.JdbcUtil.*;

public class MemberJoinProService {

	public boolean joinMember(MemberBean member) {
		boolean isjoinSuccess = false;
		int insertCount = 0;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		insertCount = memberDAO.insertMember(member);
		if(insertCount > 0) {	// 회원가입 된 경우
			isjoinSuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return isjoinSuccess;
	}

}
