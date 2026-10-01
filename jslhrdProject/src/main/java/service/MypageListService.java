package service;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.MemberDao;
import model.MypageDto;

public class MypageListService implements Command{

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		
		String userid = (String)session.getAttribute("userid");
		
		MemberDao dao = new MemberDao();
		List<MypageDto> list = dao.getFavoriteList(userid);
		
		request.setAttribute("list", list);
	}

}
