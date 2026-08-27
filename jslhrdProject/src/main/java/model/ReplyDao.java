package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;   // ← 기존에 쓰시던 DB연결 유틸로 이름 바꿔서 사용하세요

public class ReplyDao {

    // ----------------------------------------------------
    // 싱글톤 패턴 : DAO 객체를 딱 1개만 만들어서 계속 재사용
    // (new ReplyDAO() 를 여러 번 안 하고 getInstance()로 하나만 씀)
    // ----------------------------------------------------
    private static ReplyDao instance = new ReplyDao();

    public static ReplyDao getInstance() {
        return instance;
    }

    private ReplyDao() {} // 외부에서 new 로 못 만들게 생성자 숨기기


    // ======================================================
    // ① 댓글 개수 세기 : SELECT COUNT(*)
    // ======================================================
    public int getReplyCount(int port_bno) {

        int count = 0;

        // sql: port_bno가 43인 댓글이 몇 개인지 세기
        String sql = "SELECT COUNT(*) FROM reply WHERE port_bno = ?";

        // try-with-resources : { } 블록 끝나면 자동으로 자원(con, pstmt, rs) 닫아줌
        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, port_bno);   // 첫번째 ? 자리에 port_bno 값 넣기

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);  // COUNT(*) 결과값 (숫자 1개)
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }


    // ======================================================
    // ② 댓글 목록 가져오기 : SELECT * (최신순 정렬)
    // ======================================================
    public List<ReplyDto> getReplyList(int port_bno) {

        // 결과를 담을 빈 리스트 (댓글이 여러 개니까 List로)
        List<ReplyDto> list = new ArrayList<ReplyDto>();

        // 최근에 쓴 댓글이 위로 오게 reply_bno 내림차순 정렬
        String sql = "SELECT reply_bno, port_bno, userid, reply_content, reply_date "
                   + "FROM reply WHERE port_bno = ? ORDER BY reply_bno DESC";

        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, port_bno);

            try (ResultSet rs = pstmt.executeQuery()) {

                // rs.next() : 결과 한 줄씩 읽으면서 더 있으면 true, 없으면 false
                while (rs.next()) {

                    // DB 한 줄 → ReplyDTO 상자 하나로 옮겨 담기
                    ReplyDto dto = new ReplyDto();
                    dto.setBno(rs.getInt("bno"));
                    dto.setPortBno(rs.getInt("port_bno"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setReplyContent(rs.getString("reply_content"));
                    dto.setReplyDate(rs.getTimestamp("reply_date"));

                    list.add(dto);   // 리스트에 상자 하나 추가
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;   // 댓글 상자들이 담긴 리스트 반환
    }


    // ======================================================
    // ③ 댓글 등록하기 : INSERT
    // ======================================================
    public int insertReply(ReplyDto dto) {

        int result = 0; // 성공하면 1(영향받은 행 수), 실패면 0

        // reply_seq.NEXTVAL : 아까 만든 시퀀스에서 새 번호표 뽑아서 PK로 사용
        String sql = "INSERT INTO reply (reply_bno, port_bno, userid, reply_content) "
                   + "VALUES (replyseq.NEXTVAL, ?, ?, ?)";

        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, dto.getPortBno());
            pstmt.setString(2, dto.getUserid());
            pstmt.setString(3, dto.getReplyContent());

            result = pstmt.executeUpdate();   // INSERT 실행 → 성공한 행 개수 리턴

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}
