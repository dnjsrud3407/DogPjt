package member.svc;

import java.sql.Connection;

import member.dao.MemberDAO;
import member.vo.MemberBean;

import static db.JdbcUtil.*;

public class MemberLoginProService {

	public int isLoginMember(MemberBean memberBean) {
		int loginResult = 0;
		Connection con = getConnection();
		MemberDAO memberDAO = MemberDAO.getInstance();
		memberDAO.setConnection(con);
		
		// 일치하는 경우 1 / 아이디 틀린 경우 0 / 패스워드 틀린 경우 -1
		loginResult = memberDAO.selectLoginMember(memberBean);
		
		close(con);
		return loginResult;
	}

}
