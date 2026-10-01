package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.LoginService;
import service.LogoutService;
import service.MypageDeleteService;
import service.MypageListService;
import service.MypageService;
import service.SignUpService;
import service.UserIdCheck;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MemberController() {
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
		String action = request.getPathInfo();
		System.out.println("action : " + action);
		String page = null;
		HttpSession session = request.getSession(false);
		switch(action) {
		case "/signup.do":
			page = "/mem/member.jsp";
			break;
		case "/useridcheck.do":
			new UserIdCheck().doCommand(request, response);
			break;
		case "/signuppro.do":
			new SignUpService().doCommand(request, response);
			response.sendRedirect(request.getContextPath() + "/main.do");
			break;
		case "/login.do":
			page = "/mem/login.jsp";
			break;
		case "/loginpro.do":
			new LoginService().doCommand(request, response);
			break;
		case "/logout.do":
			new LogoutService().doCommand(request, response);
			response.sendRedirect(request.getContextPath() + "/main.do");
			break;
		case "/favorite.do":
			new MypageService().doCommand(request, response);
			break;
		case "/mylist.do":
			if(session == null || session.getAttribute("userid") == null) {
				response.sendRedirect(request.getContextPath() + "/member/login.do");
				return;
			}
			new MypageListService().doCommand(request, response);
			page = "/mem/mylist.jsp";
			break;
		case "/mydelete.do":
			new MypageDeleteService().doCommand(request, response);
			break;
		}
		
		if(page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

}
