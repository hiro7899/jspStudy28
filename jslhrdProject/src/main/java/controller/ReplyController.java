package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Command;
import service.ReplyListService;

@WebServlet("/reply/*.do")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    private void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        // 맨 마지막 "/" 뒤에 있는 글자만 잘라내기 → "list.do" 만 남음
        String command = uri.substring(uri.lastIndexOf("/") + 1);

        // command 값에 담긴 문자열 확인용 (개발할 때만 켜두고 나중에 지워도 됨)
        System.out.println("요청 command = " + command);

        // Service 타입 변수 하나 선언 (아직 누구인지는 모름 = null)
        Command service = new ReplyListService();

        // ---- 여기가 핵심! 요청 종류에 따라 다른 직원을 뽑음 ----
        if (command.equals("list.do")) {
            // 댓글 목록 보여주는 직원 (다음 단계에서 만들 예정)
            // service = new ReplyListService();

        } else if (command.equals("write.do")) {
            // 댓글 작성하는 직원 (다음 단계에서 만들 예정)
            // service = new ReplyWriteService();

        } else if (command.equals("delete.do")) {
            // 댓글 삭제하는 직원 (다음 단계에서 만들 예정)
            // service = new ReplyDeleteService();
        }

        if (service != null) {
            try {
                service.doCommand(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 아무 직원도 못 찾았을 때 (오타 등)
            System.out.println("일치하는 command 없음: " + command);
        }
    }
}