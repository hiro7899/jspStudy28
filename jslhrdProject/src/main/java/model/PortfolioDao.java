package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import util.DBManager;

public class PortfolioDao {

	public void portInsert(PortfolioDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = """
				INSERT INTO portfolio (bno, name, title, content, imgfile)
				VALUES (portfolioseq.NEXTVAL, ?, ?, ?, ?)
				""";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getImgfile());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
			
		} 

	}
}
