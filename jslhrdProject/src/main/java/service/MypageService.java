package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;

public class MypageService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userid") == null) {
			
			return;
		}
		int portbno = Integer.parseInt(request.getParameter("portbno"));
		String userid = request.getParameter("userid");
		MemberDao dao = new MemberDao();
		String msg="";
		int result = dao.mypageWish(userid, portbno);
		if(result > 0) {
			msg = "찜 성공";
		}else if(result < 0) {
			msg = "한 번만 찜 할 수 있어요";
		}else {
			msg = "찜 실패";
		}
		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().write(msg); //msg문자열이 $.ajax 로 자동으로 보내진다
		//setAttribute는 forward를 필요로 하기때문에 동기 방식에서만 유효하다
	}

}
