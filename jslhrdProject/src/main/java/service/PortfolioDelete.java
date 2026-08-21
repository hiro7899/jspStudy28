package service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PortfolioDao;

public class PortfolioDelete implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String userid =  request.getSession().getAttribute("userid").toString();
		if(userid == null || userid.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}	
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		PortfolioDao dao = new PortfolioDao();
		dao.deleteByBno(bno, userid);
		
	}

}
