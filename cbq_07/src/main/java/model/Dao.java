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
				SELECT * 
				FROM tbl_artist
				ORDER BY artist_id
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
	
	public List<Dto> selectMentoPoint(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Dto> list = new ArrayList<Dto>();
		
		String sql = """
				SELECT p.serial_no, a.artist_id, a.artist_name, a.artist_birth, p.point,
				    CASE WHEN p.point >= 90 THEN 'A'
				        WHEN p.point >= 80 THEN 'B'
				        WHEN p.point >= 70 THEN 'C'
				        ELSE 'F'
				    END AS grade,
				m.mento_name
				FROM tbl_artist a, tbl_mento m, tbl_point p
				WHERE a.artist_id = p.artist_id 
				    AND m.mento_id = p.mento_id
				ORDER BY p.serial_no
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Dto dto = new Dto();
				
				dto.setSerialNo(rs.getInt("serial_no"));;
				dto.setArtistId(rs.getString("artist_id"));
				dto.setArtistName(rs.getString("artist_name"));
				dto.setArtistBirth(rs.getString("artist_birth"));
				dto.setPoint(rs.getInt("point"));
				dto.setGrade(rs.getString("grade"));
				dto.setMentoName(rs.getString("mento_name"));
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	public List<Dto> selectRank(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Dto> list = new ArrayList<Dto>();
		
		String sql = """
				SELECT a.artist_id, a.artist_name, a.artist_gender,
				    SUM(p.point) AS tpoint, 
				    AVG(p.point) AS apoint,
				    RANK() OVER(ORDER BY SUM(p.point) DESC) AS rank
				FROM tbl_artist a
				JOIN tbl_point p ON a.artist_id = p.artist_id
				GROUP BY a.artist_id, a.artist_name, a.artist_gender
				ORDER BY tpoint DESC
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
				dto.setTpoint(rs.getInt("tpoint"));
				dto.setApoint(rs.getDouble("apoint"));
				dto.setRank(rs.getInt("rank"));
				
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
