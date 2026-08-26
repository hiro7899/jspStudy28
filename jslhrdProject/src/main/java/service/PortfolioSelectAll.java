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
//		List<PortfolioDto> list = dao.getSelect();

		String keyword = request.getParameter("keyword");
		if (keyword == null || keyword.trim().isEmpty()) {
			keyword = "";
		}
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
		int pageSize = 5;
		List<PortfolioDto> list = dao.getSearchAndPaging(keyword, page, pageSize);
		
		//조건에 만족하는 총 레코드 갯수
		int totalResults = dao.countSearchResults(keyword);
		
		//총 페이지 수 구하기
		int totalPage = (int)Math.ceil((double)totalResults / pageSize);
		
		//페이지 숫자가 출력되는 블럭의 수
		int pageBlock = 10;
		
		//시작 페이지 번호
		int startPage = ((page - 1)/pageBlock) * pageBlock + 1;
		
		//끝 페이지 번호
		int endPage = Math.min(startPage + pageBlock - 1, totalPage);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("currentPage", page);
		request.setAttribute("totalCount", totalResults);
	}

}
