package service;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		
		String type = request.getParameter("type");
		if(type.equals("view")) {
			dao.viewsCount(bno);
			PortfolioDto prevDto = dao.prevBno(bno);
			PortfolioDto nextDto = dao.nextBno(bno);
			request.setAttribute("prevDto", prevDto);
			request.setAttribute("nextDto", nextDto);
		}
		
		request.setAttribute("dto", dto);
	}

	
}
