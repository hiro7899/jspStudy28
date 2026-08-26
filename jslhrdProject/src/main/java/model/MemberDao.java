package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		
		public int mypageWish(String userid, int portbno) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int result = 0;
			String sql = """
					INSERT INTO mypage (bno, port_bno, userid)
					VALUES (mypageseq.NEXTVAL, ?, ?)
					""";
			try {
				conn = DBManager.getInstance();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, portbno);
				pstmt.setString(2, userid);
				
				result = pstmt.executeUpdate(); //insert가 정상적으로 처리되면 1;
				
			} catch (Exception e) {
				e.printStackTrace();
				result = -1;
			} finally {
				DBManager.close(pstmt, conn);
			}
			return result;
		}
		
		public List<MypageDto> getFavoriteList(String userid){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			List<MypageDto> list = new ArrayList<MypageDto>();			
			String sql = """
					SELECT m.bno as mbno, p.bno as pbno, p.name, p.title, p.content, p.imgfile, p.regdate, p.views, m.userid
					FROM mypage m
					JOIN portfolio p
					    ON m.port_bno = p.bno
					WHERE m.userid = ?
					""";
			try {
				conn = DBManager.getInstance();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MypageDto dto = new MypageDto();
					dto.setMbno(rs.getInt("mbno"));
					dto.setPbno(rs.getInt("pbno"));
					dto.setName(rs.getString("name"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setImgfile(rs.getString("imgfile"));
					dto.setRegdate(rs.getString("regdate"));
					dto.setViews(rs.getInt("views"));
					dto.setUserid(rs.getString("userid"));
					
					list.add(dto);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(rs, pstmt, conn);
			}
			return list;
		}
		
		public boolean deleteFavorite(int mbno) {
			boolean result = false;
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			String sql = """
					DELETE mypage WHERE bno = ?
					""";
			try {
				conn = DBManager.getInstance();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, mbno);
				int row = pstmt.executeUpdate();
				result = (row > 0);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(pstmt, conn);
			}
			
			return result;
		}
		
}
