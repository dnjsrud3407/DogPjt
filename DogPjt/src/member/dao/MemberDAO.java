package member.dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import member.vo.MemberBean;

public class MemberDAO {
	private static MemberDAO instance = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return instance;
	}
	
	Connection con;
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	// 로그인 할 때
	public int selectLoginMember(MemberBean memberBean) {
		int loginResult = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT pass FROM member WHERE id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberBean.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {		// id가 있는 경우
				String pass = rs.getString(1);
				if(pass.equals(memberBean.getPass())) {	// 비밀번호 일치하는 경우
					loginResult = 1;
				} else {		// 비밀번호 틀린 경우
					loginResult = -1;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {close(pstmt);}
			if(rs != null) {close(rs);}
		}
		
		return loginResult;
	}
	
	public int idCheck(String id) {
		int idCheck = -1;	// id가 없는 경우
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member WHERE id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(!rs.next()) {		// id가 있는 경우
				idCheck = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {close(pstmt);}
			if(rs != null) {close(rs);}
		}
		
		return idCheck;
	}
	// 회원가입 할 때
	public int insertMember(MemberBean member) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO member VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getEmail());
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {close(pstmt);}
		}
		
		return insertCount;
	}
	
}
