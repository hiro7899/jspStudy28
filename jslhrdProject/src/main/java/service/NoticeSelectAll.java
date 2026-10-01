package service;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NoticeDao;
import model.NoticeDto;

public class NoticeSelectAll implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		NoticeDao dao = new NoticeDao();
		
		List<NoticeDto> list =  dao.selectAll();
		
		request.setAttribute("list", list);

	}

}
