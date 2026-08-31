package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.NoticeSelectAll;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 2, //2MB
		maxFileSize = 1024 * 1024 * 10, //10MB
		maxRequestSize = 1024 * 1024 * 50 //50MB
)
@WebServlet("/noti/*")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeController() {
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
		
		String page = null;
		String action = request.getPathInfo();
		
		switch(action) {
		case "/list":
			new NoticeSelectAll().doCommand(request, response);
			page = "/notice/list.jsp";
			break;
		case "/write":
			page = "/notice/write.jsp";
			break;
		case "/writepro":
			response.sendRedirect(request.getContextPath() + "/noti/list");
			break;
		case "/view":
			page = "/notice/view.jsp";
			break;
		
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
