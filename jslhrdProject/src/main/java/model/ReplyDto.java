package model;

import java.sql.Timestamp;

public class ReplyDto {
	    private int bno;
	    private int portBno;
	    private String userid;
	    private String replyContent;
	    private Timestamp replyDate;
		public int getBno() {
			return bno;
		}
		public void setBno(int bno) {
			this.bno = bno;
		}
		public int getPortBno() {
			return portBno;
		}
		public void setPortBno(int portBno) {
			this.portBno = portBno;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getReplyContent() {
			return replyContent;
		}
		public void setReplyContent(String replyContent) {
			this.replyContent = replyContent;
		}
		public Timestamp getReplyDate() {
			return replyDate;
		}
		public void setReplyDate(Timestamp replyDate) {
			this.replyDate = replyDate;
		}
	    
}
