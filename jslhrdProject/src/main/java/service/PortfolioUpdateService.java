package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.PortfolioDao;
import model.PortfolioDto;

public class PortfolioUpdateService implements Command {

	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PortfolioDto dto = new PortfolioDto();
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Part imageFile = request.getPart("imgfile");
		String fileName = null;
		if(imageFile != null && imageFile.getSize() > 0) {
			
			String originalFilename = Paths.get(imageFile.getSubmittedFileName()).getFileName().toString();
			fileName = UUID.randomUUID().toString() + "_" + originalFilename;
			
			System.out.println(fileName);
			String uploadPath = "D:\\upload";
			
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
		
		dto.setTitle(title);
		dto.setContent(content);
		dto.setName(name);
		
		PortfolioDao dao = new PortfolioDao();
		dao.updatePro(dto);
	}

}
