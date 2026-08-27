package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class PortfolioDao {
	
	public List<PortfolioDto> mainList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT *
				FROM (
				    SELECT *
				    FROM portfolio
				    ORDER BY bno DESC
				)
				WHERE ROWNUM <= 3
				""";
		
		List<PortfolioDto> list = new ArrayList<PortfolioDto>();
		
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

			while (rs.next()) {
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

			if (rs.next()) {
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

	public void deleteByBno(int bno, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE FROM portfolio WHERE bno = ? AND name = ?";

		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}

	}

	public void updatePro(PortfolioDto dto, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		// Check if imgfile is BOTH not null AND not empty
		boolean hasNewImage = dto.getImgfile() != null && !dto.getImgfile().isEmpty();

		String sql = "";
		if (hasNewImage) {
			sql = """
					UPDATE portfolio
					SET title = ?, content = ?, imgfile = ? 
					WHERE bno = ? AND name = ?
					""";
		} else {
			sql = """
					UPDATE portfolio
					SET title = ?, content = ? 
					WHERE bno = ? AND name = ?
					""";
		}
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);

			if (hasNewImage) {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setString(3, dto.getImgfile());
				pstmt.setInt(4, dto.getBno());
				pstmt.setString(5, userid);
			} else {
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getBno());
				pstmt.setString(4, userid);
			}

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(pstmt, conn);
		}
	}
	//이전글
	public PortfolioDto prevBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT bno, title 
				FROM portfolio 
				WHERE bno = (
					SELECT MAX(bno) 
					FROM portfolio 
					WHERE bno < ?
				)
				""";
		PortfolioDto dto = null;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PortfolioDto();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		return dto;
	}
	//다음글
	public PortfolioDto nextBno(int bno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT bno, title 
				FROM portfolio 
				WHERE bno = (
					SELECT MIN(bno) 
					FROM portfolio 
					WHERE bno > ?
				)
				""";
		PortfolioDto dto = null;
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new PortfolioDto();
				dto.setBno(rs.getInt("bno"));
				dto.setTitle(rs.getString("title"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(rs, pstmt, conn);
		}
		return dto;
	}
	
	//클릭한 페이지에 해당하는 조건에 만족하는 레코드 검색
	public List<PortfolioDto> getSearchAndPaging(String keyword, int page, int pageSize) {
		//keyword 는 검색어
		//page는 현재 페이지 번호
		//pageSize 는 한 페이지에 보여줄 글 수
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PortfolioDto> list = new ArrayList<PortfolioDto>();

		String sql = """
				SELECT * FROM (
				    SELECT ROWNUM rn, aaa.* FROM
				        (SELECT * FROM portfolio WHERE title LIKE ? OR content LIKE ? ORDER BY bno DESC) aaa
				        WHERE ROWNUM <= ?)
				WHERE rn > ?
				""";
		int offset = (page - 1) * pageSize;
		
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setInt(3, offset + pageSize);
			pstmt.setInt(4, offset);
			rs = pstmt.executeQuery();

			while (rs.next()) {
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
	
	public int countSearchResults(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = """
				SELECT COUNT(*) 
				FROM portfolio 
				WHERE title LIKE ? OR content LIKE ?
				""";
		try {
			conn = DBManager.getInstance();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
				//검색된 첫번째 컬럼값을 정수타입으로 가져온후 리턴한다
				//컬럼을 지정할 때 컬럼명 대신 숫자를 사용할 수 있다
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
