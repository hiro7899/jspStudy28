package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ReplyDao;
import model.ReplyDto;

public class ReplyListService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ---- 1) 클라이언트(ajax)가 보낸 파라미터 받기 ----
        // 예: /reply/list.do?port_bno=43
        int port_bno = Integer.parseInt(request.getParameter("port_bno"));

        // ---- 2) DAO 시켜서 진짜 데이터 가져오기 ----
        ReplyDao dao = ReplyDao.getInstance();
        List<ReplyDto> list = dao.getReplyList(port_bno);
        int count = dao.getReplyCount(port_bno);

        // ---- 3) 목록 + 개수를 하나의 JSON으로 묶어서 보낼 준비 ----
        // Map 하나에 "list"랑 "count"를 같이 담음
        java.util.Map<String, Object> resultMap = new java.util.HashMap<>();
        resultMap.put("list", list);
        resultMap.put("count", count);

        // ---- 4) 자바 객체를 JSON 문자열로 변환 (Gson 라이브러리) ----
        Gson gson = new Gson();
        String jsonData = gson.toJson(resultMap);

        // ---- 5) 응답 설정하고 클라이언트(ajax)한테 JSON 그대로 뿌려주기 ----
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonData);
        out.flush();
	}

}
