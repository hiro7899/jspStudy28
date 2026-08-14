package service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class BlogWriteService implements Command {

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
			
		}
	}

}
