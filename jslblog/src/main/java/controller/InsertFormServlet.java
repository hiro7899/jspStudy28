package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.ExDTO;

//서블릿이란?
//자바를 이용해서 웹에서 실행되는 프로그램을 작성하는 기술이다
//서블릿은 자바 클래스 형태의 웹 애플리케이션을 말한다
//브라우저를 통해 자바 클래스가 실행되도록 하기 위해서 java.servlet.http 패키지
//에서 제공하는 HttpServlet 클래스를 상속받아 구현한다


@WebServlet("/memberWrite.do")
public class InsertFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InsertFormServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String[] lang = request.getParameterValues("language");
		String answer = request.getParameter("answer");
		
		//출력할 값은 attribute 속성에 담아서 포워드 시킨다
//		request.setAttribute("id", id);
//		request.setAttribute("pw", pw);
//		request.setAttribute("language", lang);
//		request.setAttribute("answer", answer);
		
		ExDTO dto = new ExDTO();
		
		dto.setId(id);
		dto.setPw(pw);
		dto.setLang(lang);
		dto.setAnswer(answer);
		
		request.setAttribute("exdto", dto);
		
		RequestDispatcher rd = request.getRequestDispatcher("ex/print.jsp");
		rd.forward(request, response);
	}

}
