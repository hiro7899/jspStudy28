package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<PortfolioDto> getSelect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PortfolioDto> list = new ArrayList<PortfolioDto>();
		
		String sql = "SELECT * FROM portfolio ORDER BY bno DESC";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PortfolioDto dto = new PortfolioDto();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViews(rs.getInt("views"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public PortfolioDto getSelectOne(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PortfolioDto dto = null;	
		String sql = "SELECT * FROM portfolio WHERE bno = " + bno;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PortfolioDto();
				dto.setBno(rs.getInt("bno"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setImgfile(rs.getString("imgfile"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setViews(rs.getInt("views"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		
		return dto;
	}
	
	public void viewsCount(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE portfolio SET views = views + 1 WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
	}
	
	public void deleteByBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM portfolio WHERE bno = ?";
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
	}
	
	public void updatePro(PortfolioDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		if(dto.getImgfile() != null || !dto.getImgfile().isEmpty()) {
			sql = """
					UPDATE portfolio 
					SET title = ?, content = ?, imgfile = ? WHERE bno = ?
					""";
		}else {
			sql = """
					UPDATE portfolio 
					SET title = ?, content = ? WHERE bno = ?
					""";
		}
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			if(dto.getImgfile() != null || !dto.getImgfile().isEmpty()) {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getImgfile());
				pstmt.setInt(4, dto.getBno());
			}else {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getBno());
			}
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
		
	}
	
}
