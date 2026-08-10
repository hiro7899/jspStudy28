package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBManager;

public class MemberDao {

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
}
