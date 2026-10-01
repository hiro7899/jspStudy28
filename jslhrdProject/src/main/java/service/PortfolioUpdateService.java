package service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.PortfolioDao;
import model.PortfolioDto;

public class PortfolioUpdateService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String userid =  request.getSession().getAttribute("userid").toString();
		if(userid == null || userid.equals("")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}

		PortfolioDto dto = new PortfolioDto();
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imageFile = request.getPart("imgfile");
		String fileName = null;
		if(imageFile != null && imageFile.getSize() > 0) {
			
			String originalFilename = Paths.get(imageFile.getSubmittedFileName()).getFileName().toString();
			fileName = UUID.randomUUID().toString() + "_" + originalFilename;
			
			System.out.println(fileName);
			String os = System.getProperty("os.name").toLowerCase();
	         String uploadPath;

	         if (os.contains("win")) {
	             uploadPath = "D:/upload"; // 내 컴퓨터 (Windows)
	         } else {
	             uploadPath = "/var/upload"; // 우분투 서버 (Linux)
	         }
			
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			String filePath = uploadPath + File.separator + fileName;
			System.out.println(filePath);
			imageFile.write(filePath);
			
			System.out.println("파일 저장 완료");
			dto.setImgfile(fileName);
		}
		String name = request.getParameter("name");
		dto.setBno(bno);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setName(name);
		
		PortfolioDao dao = new PortfolioDao();
		dao.updatePro(dto, userid);
	}

}
