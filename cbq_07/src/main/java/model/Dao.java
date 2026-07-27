package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Dao {

	public void insert(Dto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
				INSERT INTO tbl_artist (artist_id, artist_name, artist_gender, artist_birth, talent, agency)
				VALUES (?, ?, ?, ?, ?, ?)
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getArtistId());
			pstmt.setString(2, dto.getArtistName());
			pstmt.setString(3, dto.getArtistGender());
			pstmt.setString(4, dto.getArtistBirth());
			pstmt.setString(5, dto.getTalent());
			pstmt.setString(6, dto.getAgency());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(pstmt, conn);
		}
	}
	
	public List<Dto> selectAll(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Dto> list = new ArrayList<Dto>();
		
		String sql = """
				SELECT * FROM tbl_artist
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Dto dto = new Dto();
				
				dto.setArtistId(rs.getString("artist_id"));
				dto.setArtistName(rs.getString("artist_name"));
				dto.setArtistGender(rs.getString("artist_gender"));
				dto.setArtistBirth(rs.getString("artist_birth"));
				dto.setTalent(rs.getString("talent"));
				dto.setAgency(rs.getString("agency"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(rs, pstmt, conn);
		}
		
		return list;
	}
}
