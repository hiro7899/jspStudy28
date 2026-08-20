package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PortfolioDao;
import model.PortfolioDto;

public class PortfolioSelectBno implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		PortfolioDao dao = new PortfolioDao();
		PortfolioDto dto = dao.getSelectOne(bno);
		
		request.setAttribute("dto", dto);
	}

	
}
