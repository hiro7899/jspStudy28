package service;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PortfolioDao;
import model.PortfolioDto;

public class MainPortNews implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		PortfolioDao dao = new PortfolioDao();
		
		List<PortfolioDto> list = dao.mainList();
		request.setAttribute("list", list);
	}

}
