package com.jsp.board.post.model.vo;

public class Post {

	private int boardNo;	// 게시글 번호
	
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardPw;
	
	private int readCount;
	
	private String createDate;
	private String modifyDate;
	private String deleteDate;
	
	private int statusCode;

	// 기본 생성자
	public Post() {
		// TODO Auto-generated constructor stub
	}
	
	// getter/setter
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardPw() {
		return boardPw;
	}

	public void setBoardPw(String boardPw) {
		this.boardPw = boardPw;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	// toString()
	@Override
	public String toString() {
		return "Post [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", boardPw=" + boardPw + ", readCount=" + readCount + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", deleteDate=" + deleteDate + ", statusCode="
				+ statusCode + "]";
	}
	
	
}
