package dog.dao;

import java.sql.Connection;
import static db.JdbcUtil.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.xml.internal.ws.Closeable;

import dog.vo.DogBean;

public class DogDAO {
	private static DogDAO instance = new DogDAO();
	private DogDAO() {}
	public static DogDAO getInstance() {
		return instance;
	}
	
	Connection con = null;
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public int insertDog(DogBean dogBean) {
		int insertCount = 0;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO dog VALUES(null,?,?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dogBean.getKind());
			pstmt.setInt(2, dogBean.getPrice());
			pstmt.setString(3, dogBean.getImage());
			pstmt.setString(4, dogBean.getCountry());
			pstmt.setInt(5, dogBean.getHeight());
			pstmt.setInt(6, dogBean.getWeight());
			pstmt.setString(7, dogBean.getContent());
			pstmt.setInt(8, 0);
			insertCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {close(pstmt);}
		}
		
		return insertCount;
	}
	public ArrayList<DogBean> selectDogList() {
		ArrayList<DogBean> dogList = new ArrayList<DogBean>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT * from dog";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DogBean dogBean = new DogBean();
				dogBean.setId(rs.getInt("id"));
				dogBean.setKind(rs.getString("kind"));
				dogBean.setPrice(rs.getInt("price"));
				dogBean.setImage(rs.getString("image"));
				dogBean.setCountry(rs.getString("country"));
				dogBean.setHeight(rs.getInt("height"));
				dogBean.setWeight(rs.getInt("weight"));
				dogBean.setContent(rs.getString("content"));
				dogBean.setReadcount(rs.getInt("readcount"));
				dogList.add(dogBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {close(rs);}
			if(pstmt != null) {close(pstmt);}
		}
		
		
		return dogList;
	}
	
	// id 에 해당하는 개 정보 가져오기
	public DogBean selectDog(int id) {
		DogBean dogBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM dog WHERE id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dogBean = new DogBean();
				dogBean.setId(rs.getInt("id"));
				dogBean.setKind(rs.getString("kind"));
				dogBean.setPrice(rs.getInt("price"));
				dogBean.setImage(rs.getString("image"));
				dogBean.setCountry(rs.getString("country"));
				dogBean.setHeight(rs.getInt("height"));
				dogBean.setWeight(rs.getInt("weight"));
				dogBean.setContent(rs.getString("content"));
				dogBean.setReadcount(rs.getInt("readcount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {close(rs);}
			if(pstmt != null) {close(pstmt);}
		}
		return dogBean;
	}
	
	// 조회수 1 증가 작업
	public int updateReadcount(int id) {
		PreparedStatement pstmt = null;
		int updatecount = 0;
		
		String sql = "UPDATE dog SET readcount=readcount+1 WHERE id=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			updatecount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {close(pstmt);}
		}
		return updatecount;
	}
	
	
}


































