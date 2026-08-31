package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class NoticeDao {

	public List<NoticeDto> selectAll(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT n.*, writer 
				FROM notice n
				JOIN member m ON n.userid = m.userid
				ORDER BY bno DESC
				""";
		
		List<NoticeDto> list = new ArrayList<NoticeDto>();
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeDto dto = new NoticeDto();
				
				dto.setBno(rs.getInt("bno"));
				dto.setUserid(rs.getString("userid"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImgfile(rs.getString("imgfile"));
				
				dto.setWriter(rs.getString("writer"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
}
