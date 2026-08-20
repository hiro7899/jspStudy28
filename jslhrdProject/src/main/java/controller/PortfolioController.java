package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BlogWriteService;
import service.PortfolioDelete;
import service.PortfolioSelectAll;
import service.PortfolioSelectBno;
import service.PortfolioUpdateService;

@WebServlet("/port/*")
@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2, //2MB
		maxFileSize = 1024 * 1024 * 10, //10MB
		maxRequestSize = 1024 * 1024 * 50 //50MB
)//@MultipartConfig 이 서블릿은 multipart/form-data 방식의 파일 업로드를 처리한다
//fileSizeThreshold 파일을 메모리에서 처리할지 임시 파일로 저장할지 결정하는 기준크기
//maxFileSize 파일 하나의 최대크기
//maxRequestSize HTTP 요청 전체의 최대크기(타이틀, 컨텐츠, 첨부파일, 글쓴이) 전체 합친 용량

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
			new PortfolioSelectAll().doCommand(request, response);
			break;
		case "/write.do":
			HttpSession session = request.getSession();
			Object userid = session.getAttribute("userid");
			if(userid == null) {
				response.sendRedirect(request.getContextPath() + "/member/login.do");
				return;
			}
			page = "/portfolio/write.jsp";
			break;
		case "/writepro.do":
			new BlogWriteService().doCommand(request, response);
			response.sendRedirect(request.getContextPath() + "/port/list.do");
			
			break;
		case "/view.do":
			page = "/portfolio/view.jsp";
			new PortfolioSelectBno().doCommand(request, response);
			break;
		case "/delete.do":
			new PortfolioDelete().doCommand(request, response);
			response.sendRedirect(request.getContextPath() + "/port/list.do");
			break;
		case "/update.do":
			page = "/portfolio/update.jsp";
			new PortfolioSelectBno().doCommand(request, response);
			break;
		case "/updatepro.do":
			new PortfolioUpdateService().doCommand(request, response);
			response.sendRedirect(request.getContextPath() + "/port/list.do");
			break;
		}
		
		//page가 null이 아니면 포워딩
		if(page != null) {
			request.getRequestDispatcher(page).forward(request, response);
		}
		
	}

}
