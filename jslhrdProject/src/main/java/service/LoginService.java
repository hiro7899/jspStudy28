package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import model.MemberDto;
import util.PasswordUtil;

public class LoginService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		MemberDto dto = new MemberDao().searchByIdPw(userid);

		if(dto != null && PasswordUtil.checkPassword(password, dto.getPassword())) {
			//세션생성
			HttpSession session = request.getSession();
			session.setAttribute("userid", userid);
			
			response.getWriter().write("success");
		}else {
			response.getWriter().write("fail");
		}
	}

}
