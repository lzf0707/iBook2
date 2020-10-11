package com.mall.model;

public class BookCategory {

	private Long bookCategoryId;
	private String bookCategoryName;
	private String bookCategoryDesc;
	private String bookCategoryImg;
	private Integer priority;
	private String createTime;
	private String lastEditTime;
	private BookCategory parent;

	public Long getbookCategoryId() {
		return bookCategoryId;
	}

	public void setbookCategoryId(Long bookCategoryId) {
		this.bookCategoryId = bookCategoryId;
	}

	public String getBookCategoryName() {
		return bookCategoryName;
	}

	public void setBookCategoryName(String bookCategoryName) {
		this.bookCategoryName = bookCategoryName;
	}

	public String getBookCategoryDesc() {
		return bookCategoryDesc;
	}

	public void setBookCategoryDesc(String bookCategoryDesc) {
		this.bookCategoryDesc = bookCategoryDesc;
	}

	public String getBookCategoryImg() {
		return bookCategoryImg;
	}

	public void setBookCategoryImg(String bookCategoryImg) {
		this.bookCategoryImg = bookCategoryImg;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(String lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	public BookCategory getParent() {
		return parent;
	}

	public void setParent(BookCategory parent) {
		this.parent = parent;
	}

	public BookCategory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BookCategory [bookCategoryId=" + bookCategoryId + ", bookCategoryName=" + bookCategoryName
				+ ", bookCategoryDesc=" + bookCategoryDesc + ", bookCategoryImg=" + bookCategoryImg + ", priority="
				+ priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", parent=" + parent
				+ "]";
	}
	
	

}
