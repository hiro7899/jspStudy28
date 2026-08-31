package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class ReplyDao {

    private static ReplyDao instance = new ReplyDao();

    public static ReplyDao getInstance() {
        return instance;
    }

    private ReplyDao() {}


    // ======================================================
    // ① 댓글 개수 세기 : SELECT COUNT(*)
    // ======================================================
    public int getReplyCount(int port_bno) {

        int count = 0;

        String sql = "SELECT COUNT(*) FROM reply WHERE port_bno = ?";

        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, port_bno);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
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

        List<ReplyDto> list = new ArrayList<ReplyDto>();

        // 💡 reply_bno -> bno 로 변경
        String sql = "SELECT bno, port_bno, userid, reply_content, reply_date "
                   + "FROM reply WHERE port_bno = ? ORDER BY bno DESC";

        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, port_bno);

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    ReplyDto dto = new ReplyDto();
                    dto.setBno(rs.getInt("bno")); // 컬럼명 bno 유지
                    dto.setPortBno(rs.getInt("port_bno"));
                    dto.setUserid(rs.getString("userid"));
                    dto.setReplyContent(rs.getString("reply_content"));
                    dto.setReplyDate(rs.getTimestamp("reply_date"));

                    list.add(dto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    // ======================================================
    // ③ 댓글 등록하기 : INSERT
    // ======================================================
    public int insertReply(ReplyDto dto) {

        int result = 0;

        // 💡 reply_bno -> bno 로 변경
        String sql = "INSERT INTO reply (bno, port_bno, userid, reply_content) "
                   + "VALUES (replyseq.NEXTVAL, ?, ?, ?)";

        try (Connection con = DBManager.getInstance();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, dto.getPortBno());
            pstmt.setString(2, dto.getUserid());
            pstmt.setString(3, dto.getReplyContent());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}