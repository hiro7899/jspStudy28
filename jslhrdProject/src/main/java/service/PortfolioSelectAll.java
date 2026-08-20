package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PortfolioDao;
import model.PortfolioDto;

public class PortfolioSelectAll implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		PortfolioDao dao = new PortfolioDao();
		List<PortfolioDto> list = dao.getSelect();
		
		request.setAttribute("list", list);

	}

}
