package service;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDao;

public class UserIdCheck implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String userid = request.getParameter("userid");
		
		Integer result = new MemberDao().useridFind(userid);
		
		//클라이언트 결과를 $.ajax() 로 보내야한다
		PrintWriter out  = response.getWriter();
		out.print(result);
	}
}
