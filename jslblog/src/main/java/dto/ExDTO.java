package dto;

public class ExDTO {
	
	private String id;
	private String pw;
	private String[] lang;
	private String answer;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String[] getLang() {
		return lang;
	}
	public void setLang(String[] lang) {
		this.lang = lang;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
