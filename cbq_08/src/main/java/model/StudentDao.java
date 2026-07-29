package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

	public void insertStudent(Student student) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
				INSERT INTO tbl_student (syear, sclass, sno, sname, birth, gender, tel1, tel2, tel3)
				VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, student.getSyear());
			pstmt.setString(2, student.getSclass());
			pstmt.setString(3, student.getSno());
			pstmt.setString(4, student.getSname());
			pstmt.setString(5, student.getBirth());
			pstmt.setString(6, student.getGender());
			pstmt.setString(7, student.getTel1());
			pstmt.setString(8, student.getTel2());
			pstmt.setString(9, student.getTel3());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(pstmt, conn);
		}
	}
	
	public void insertScore(Student student) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = """
				INSERT INTO tbl_score (syear, sclass, sno, kor, eng, mat)
				VALUES (?, ?, ?, ?, ?, ?)
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, student.getSyear());
			pstmt.setString(2, student.getSclass());
			pstmt.setString(3, student.getSno());
			pstmt.setInt(4, student.getKor());
			pstmt.setInt(5, student.getEng());
			pstmt.setInt(6, student.getMat());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(pstmt, conn);
		}
	}
	
	public List<Student> selectScore(){
		List<Student> list = new ArrayList<Student>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT stu.syear, stu.sclass, stu.sno, stu.sname, stu.gender,
				    sc.kor, sc.eng, sc.mat,
				    sc.kor + sc.eng + sc.mat AS total,
				    (sc.kor + sc.eng + sc.mat)/3 AS average
				FROM tbl_student stu, tbl_score sc
				WHERE stu.syear = sc.syear 
				    AND stu.sclass = sc.sclass 
				    AND stu.sno = sc.sno 
				ORDER BY stu.syear, stu.sclass, stu.sno
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				
				student.setSyear(rs.getString("syear"));
				student.setSclass(rs.getString("sclass"));
				student.setSno(rs.getString("sno"));
				student.setSname(rs.getString("sname"));
				student.setGender(rs.getString("gender"));
				student.setKor(rs.getInt("kor"));
				student.setEng(rs.getInt("eng"));
				student.setMat(rs.getInt("mat"));
				student.setTotal(rs.getInt("total"));
				student.setAverage(rs.getDouble("average"));
				
				list.add(student);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(rs, pstmt, conn);
		}
		return list;
	}
	
	public List<Student> selectDept(){
		List<Student> list = new ArrayList<Student>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = """
				SELECT d.syear, d.sclass, d.tname,
				    SUM(kor) AS tkor, SUM(eng) AS teng, SUM(mat) AS tmat,
				    AVG(kor) AS akor, AVG(eng) AS aeng, AVG(mat) AS amat
				FROM tbl_score s, tbl_dept d
				WHERE d.syear = s.syear 
				    AND d.sclass = s.sclass
				GROUP BY d.syear, d.sclass, d.tname
				ORDER BY tname
				""";
		
		try {
			conn = DBmanager.getInstance();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				
				student.setSyear(rs.getString("syear"));
				student.setSclass(rs.getString("sclass"));
				student.setTname(rs.getString("tname"));
				student.setTkor(rs.getInt("tkor"));
				student.setTeng(rs.getInt("teng"));
				student.setTmat(rs.getInt("tmat"));
				student.setAkor(rs.getDouble("akor"));
				student.setAeng(rs.getDouble("aeng"));
				student.setAmat(rs.getDouble("amat"));
				
				list.add(student);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(rs, pstmt, conn);
		}
		return list;
	}
}
