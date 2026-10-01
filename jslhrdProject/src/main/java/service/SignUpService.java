package service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDao;
import model.MemberDto;
import util.PasswordUtil;

public class SignUpService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDto dto = new MemberDto();
		
		dto.setWriter(request.getParameter("writer"));
		dto.setUserid(request.getParameter("userid"));
		
		String pw = request.getParameter("password");
		dto.setPassword(PasswordUtil.hashPassword(pw));
		
		dto.setPhone(request.getParameter("phone"));
		dto.setEmail(request.getParameter("email"));
		
		MemberDao dao = new MemberDao();
		dao.memberSave(dto);

	}

}
