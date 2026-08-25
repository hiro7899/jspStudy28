package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDao;
import model.MypageDto;

public class MypageListService implements Command{

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("userid") == null) {
			response.sendRedirect(request.getContextPath() + "/member/login.do");
			return;
		}
		String userid = (String)session.getAttribute("userid");
		
		MemberDao dao = new MemberDao();
		List<MypageDto> list = dao.getFavoriteList(userid);
		
		request.setAttribute("list", list);
	}

}
