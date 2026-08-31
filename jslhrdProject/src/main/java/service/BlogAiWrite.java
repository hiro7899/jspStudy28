package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class BlogAiWrite implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String title = request.getParameter("title");

		AiService ai = new AiService();
		
//		try {
//			ai.showModels();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		String content = "";

		try {
			content = ai.makeBlogContent(title);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("application/json; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("content", content);

		response.getWriter().print(json.toString());
	}
}
