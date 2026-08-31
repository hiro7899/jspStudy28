package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.Command;
import service.ReplyListService;
import service.ReplyWriteService;

@WebServlet("/reply/*")
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
        String command = uri.substring(uri.lastIndexOf("/") + 1);

        // ---------------------------------------------------
        // 🔒 로그인 체크 : write.do (댓글 작성)는 로그인한 사람만 가능
        // ---------------------------------------------------
        if (command.equals("write.do")) {

            HttpSession session = request.getSession();
            String userid = (String) session.getAttribute("userid");

            // 세션에 userid가 없다는 건 = 로그인 안 한 상태
            if (userid == null) {
                // 화면 이동(forward) 대신, JSON으로 "로그인 필요" 알려주고 끝냄
                // (ajax로 요청이 오기 때문에 페이지 이동이 아니라 응답만 내려줌)
                response.setContentType("application/json; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.print("{\"result\":\"login_required\"}");
                out.flush();
                return;   // 여기서 끝! 아래 코드는 실행 안 됨
            }
        }

        // ---------------------------------------------------
        // 요청(command)에 맞는 담당 직원(Command 구현체) 뽑기
        // ---------------------------------------------------
        Command command_obj = null;

        if (command.equals("list.do")) {
            command_obj = new ReplyListService();

        } else if (command.equals("write.do")) {
            command_obj = new ReplyWriteService();

        }
        // 나중에 삭제 기능 추가하고 싶으면 여기 else if 로 추가하면 됨
        // } else if (command.equals("delete.do")) {
        //     command_obj = new ReplyDeleteService();
        // }

        // 뽑힌 직원한테 실제 일 시키기
        if (command_obj != null) {
            command_obj.doCommand(request, response);
        } else {
            System.out.println("일치하는 command 없음: " + command);
        }
    }
}