package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class BlogTranslate implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");

		AiService ai = new AiService();
		String translated = "";

		try {
			translated = ai.translateToJapanese(content);
		} catch (Exception e) {
			e.printStackTrace();
		}

		response.setContentType("application/json; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("translated", translated);

		response.getWriter().print(json.toString());
	}
}
