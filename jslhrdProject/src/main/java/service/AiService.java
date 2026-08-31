package service;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

import org.json.JSONObject;

public class AiService {
	private String apiKey;
	private String model;
	
	public AiService() {
		try {
			Properties prop = new Properties();
			//key-value 설정파일을 읽기 위한 객체
			InputStream is = getClass().getClassLoader()
					//WEB-INF/classes 를 가리키는 통로
					.getResourceAsStream("config.properties");
			//classes 폴더 안에 있는 config.properties 파일을 스트림으로 읽음
			prop.load(is);
			//파일 내용을 메모리로 로딩
			apiKey = prop.getProperty("groq.api.key").trim();
			//groq.api.key=값 중에서 값만 꺼냄// API 키 추출
			model = prop.getProperty("groq.model").trim(); // 모델명 추출
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

// ✅ 공통 Groq 호출 메서드
private String callGroq(String prompt) throws Exception {
String safePrompt = prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
//줄바꿈이나 큰따옴표(") 같은 특수문자가 들어가면 JSON 형식이 깨지기 때문에, 안전하게 패스(\, \", \n) 처리해 주는 필수 작업이야.
		String jsonBody = """
			{
			"model": "openai/gpt-oss-20b",
			"messages": [
				{"role": "user", "content": "%s"}
			]
			}
			""".formatted(safePrompt);
		//""" """ 텍스트 블록 줄바꿈 OK \n 안 써도 됨 JSON 쓰기 최고
		//"%s" 는 뭐냐? prompt 값을 끼워 넣겠다는 표시
		//.formatted(prompt) 는 뭐냐? %s 자리에 prompt 문자열을 넣어준다
		// { } 객체아님 JSON 모양의 문자열
		HttpClient client = HttpClient.newHttpClient();
		//외부 서버랑 통신하는 전화기, HttpRequest 전화 내용, HttpResponse 상대방 답변
		HttpRequest request = HttpRequest.newBuilder()
				//Groq 서버에 보낼 요청서
				.uri(URI.create("https://api.groq.com/openai/v1/chat/completions"))
				//전화 걸 대상 (Groq 서버 주소)
				.header("Content-Type", "application/json")
				//“JSON 형식으로 보낼게요”
		.header("Authorization", "Bearer " + apiKey)
		//“내 API 키로 인증할게요”
		.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
		//보낼 내용(= 아까 만든 JSON 문자열)
		.build();
		//요청서 완성!
		//HttpServletRequest 브라우저 → 내 서버, HttpRequest 내 서버 → 외부 서버(Groq)
		//완전히 다른 놈들
		HttpResponse<String> response =
				client.send(request, HttpResponse.BodyHandlers.ofString());
				//client.send(요청서, "문자열로 받아라"); 응답 내용을 String으로 주세요”
				System.out.println("Groq Response:");
				System.out.println(response.body());

				JSONObject json = new JSONObject(response.body());
				//문자열(JSON) → 자바 객체로 변환
				//구조 다시 보기
				//choices
				//└─ [0]
				// └─ message
				// └─ content
				
				if (json.has("error")) {
					throw new RuntimeException(
					json.getJSONObject("error").getString("message")
					);
				}
				return json
				.getJSONArray("choices")
				//choices 배열 꺼냄
				.getJSONObject(0)
				//첫 번째 응답 선택
				.getJSONObject("message")
				//message 객체 꺼냄
				.getString("content");
				//AI가 쓴 글 내용만 추출
				}

	// 블로그 글 생성
	public String makeBlogContent(String title) throws Exception {
		String prompt = "다음 제목으로 블로그 글을 작성해줘:\n" + title;
		return callGroq(prompt);
	}

	// 한글 → 일본어 번역
	public String translateToJapanese(String text) throws Exception {
		String prompt = "You are a translation engine.\n" + "Translate the following Korean text into Japanese.\n"
				+ "Output ONLY Japanese. Do NOT include Korean. Do NOT explain.\n\n" + text;

		return callGroq(prompt);
	}

	public void showModels() throws Exception {

	    HttpClient client = HttpClient.newHttpClient();

	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create("https://api.groq.com/openai/v1/models"))
	            .header("Authorization", "Bearer " + apiKey)
	            .GET()
	            .build();

	    HttpResponse<String> response =
	            client.send(request, HttpResponse.BodyHandlers.ofString());

	    System.out.println("HTTP 상태 : " + response.statusCode());
	    System.out.println(response.body());
	}
}
