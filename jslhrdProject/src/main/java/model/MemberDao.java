package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBManager;

public class MemberDao {

	//아이디 존재 여부 검색
	public int useridFind(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		String sql = "SELECT userid FROM member WHERE userid = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
			}else {
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		return result;
	}
	
	//회원가입 저장
	public void memberSave(MemberDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
				INSERT INTO member (writer, userid, password, email, phone)
				VALUES (?, ?, ?, ?, ?)
				""";
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getPhone());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
	}
	
	//로그인할 때 아이디로 모든 컬럼 검색
		public MemberDto searchByIdPw(String userid) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "SELECT * FROM member WHERE userid = ?";
			
			MemberDto dto = null;
			
			try {
				conn = DBManager.getInstance();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					dto = new MemberDto();
					dto.setWriter(rs.getString("writer"));
					dto.setUserid(rs.getString("userid"));
					dto.setPassword(rs.getString("password"));
					dto.setPhone(rs.getString("phone"));
					dto.setEmail(rs.getString("email"));
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(rs, pstmt, conn);
			}
			return dto;
		}
}
