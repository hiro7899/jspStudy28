package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/port/*")
public class PortfolioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PortfolioController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//port/list.do 요청이 들어오면 /portfolio/list.jsp로 포워딩
		String action = request.getPathInfo();
		
		System.out.println("action : " + action);
		
		String page = null;
		switch(action) {
		case "/list.do":
			page = "/portfolio/list.jsp";
			break;
		case "/write.do":
			page = "/portfolio/write.jsp";
			break;
		case "/view.do":
			page = "/portfolio/view.jsp";
			break;
		}
		
		//page가 null이 아니면 포워딩
		if(page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

}
