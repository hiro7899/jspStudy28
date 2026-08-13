package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService implements Command{

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		//기존세션이 있으면 가져오고 없으면 null 을 반환한다
		//세션을 생성하지 않고 기존 세션만 가져온다
		
		if(session != null) {
			session.invalidate(); //세션삭제
		}
	}

}
