package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.ReplyDao;
import model.ReplyDto;

public class ReplyWriteService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // ---- 1) 한글 깨짐 방지 ----
        request.setCharacterEncoding("UTF-8");

        // ---- 2) 파라미터 받기 ----
        int port_bno = Integer.parseInt(request.getParameter("port_bno"));
        String reply_content = request.getParameter("reply_content");

        // 로그인한 사용자 아이디는 절대 클라이언트가 보낸 값을 그대로 믿으면 안 됨!
        // (누구나 위조해서 다른 사람 아이디로 댓글 달 수 있으니까)
        // 반드시 세션(session)에 저장된 로그인 정보에서 꺼내와야 함
        String userid = (String) request.getSession().getAttribute("userid");

        // ---- 3) DTO 상자에 담기 ----
        ReplyDto dto = new ReplyDto();
        dto.setPortBno(port_bno);
        dto.setUserid(userid);
        dto.setReplyContent(reply_content);

        // ---- 4) DAO 시켜서 INSERT 실행 ----
        ReplyDao dao = ReplyDao.getInstance();
        int result = dao.insertReply(dto);   // 성공하면 1, 실패하면 0

        // ---- 5) 성공/실패 여부를 JSON으로 응답 ----
        java.util.Map<String, Object> resultMap = new java.util.HashMap<>();
        resultMap.put("result", result);   // 1이면 성공, 0이면 실패

        Gson gson = new Gson();
        String jsonData = gson.toJson(resultMap);

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();

	}

}
