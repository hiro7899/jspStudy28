package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.PortfolioDao;
import model.PortfolioDto;

public class BlogWriteService implements Command {
/*
	자바의 File 클래스는 java.io 패키지에 속하며, 파일과 폴더(디렉토리) 경로를 다루고 관리하는 데 쓰이는 객체 지향적 표현입니다. 실제 파일 안의 데이터를 읽거나 쓰는 기능은 하지 않으며, 파일의 정보(메타데이터)나 경로를 제어합니다
	
	--.주요 특징
	
	파일과 디렉토리 통합: 파일과 폴더를 똑같은 File 객체로 다룹니다.
	
	존재 여부 무관: 객체를 만든다고 해서 실제 디스크에 파일이 생성되지는 않습니다.
	
	데이터 입출력 불가: 파일의 내용을 읽고 쓰려면 FileInputStream이나 FileWriter 같은 스트림(Stream) 객체에 File 객체를 전달해야 합니다.
	
	주요 기능 및 메서드
	
	exists(): 파일이나 폴더가 실제로 있는지 확인합니다.
	createNewFile(): 새로운 빈 파일을 만듭니다.
	mkdir() / mkdirs(): 새로운 폴더를 만듭니다.
	delete(): 파일이나 폴더를 지웁니다.
	getName(): 파일 이름을 가져옵니다.length(): 파일 크기를 바이트 단위로 가져옵니다
 */
	@Override
	public void doCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//블로그에서 글쓴 내용을 요청하는 작업과 dto 에 저장하는 작업
		//dto 를 dao 클래스로 넘겨서 테이블에 저장하는 기능
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//첨부파일은 getPrameter() 메서드로 요청할 수 없다.
		Part imageFile = request.getPart("imgfile");
		//Part는 servlet api에서 제공하는 인터페이스이다
		//Part는 multipart/form-data로 전송된 하나의 데이터 조각(part)을 나타낸다.
		//사용자가 form을 통해 서버로 보내는 파일 하나를 표현하는 객체
		
		String fileName = null;
		if(imageFile != null && imageFile.getSize() > 0) {
			//getSize() 첨부파일 용량
			
			String originalFilename = Paths.get(imageFile.getSubmittedFileName()).getFileName().toString();
			//getSubmittedFileName() : 사용자가 업로드한 파일의 원래 이름을 가져오는 메서드
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
			//파일 전체 경로 설정
			//File.separator : 윈도우 운영체제 \, 리눅스 운영체제 /
			System.out.println(filePath);
			imageFile.write(filePath);
			
			System.out.println("파일 저장 완료");
		}
		String name = request.getParameter("name");
		
		PortfolioDto dto = new PortfolioDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImgfile(fileName);
		dto.setName(name);
		
		PortfolioDao dao = new PortfolioDao();
		dao.portInsert(dto);
	}

}
