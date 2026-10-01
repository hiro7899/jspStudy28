package service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDao;

public class MypageDeleteService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int mbno = Integer.parseInt(request.getParameter("mbno"));
		boolean result = new MemberDao().deleteFavorite(mbno);
		
		response.getWriter().write(result ? "success" : "fail");
	}

}
