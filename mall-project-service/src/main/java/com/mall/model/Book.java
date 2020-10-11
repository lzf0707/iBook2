package com.mall.model;

public class Book {

	private Long bookId;
	private PersonInfo ower;
	private Area area;
	private BookCategory bookCategory;
	private String bookName;
	private String bookDesc;
	private String bookImg;
	private Integer priority;
	private String createTime;
	private String lastEditTime;
	private Integer enableStatus;
	private Integer endStatus;
	private String author;
	
	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public PersonInfo getOwer() {
		return ower;
	}

	public void setOwer(PersonInfo ower) {
		this.ower = ower;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public BookCategory getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(BookCategory bookCategory) {
		this.bookCategory = bookCategory;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
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

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Integer getEndStatus() {
		return endStatus;
	}

	public void setEndStatus(Integer endStatus) {
		this.endStatus = endStatus;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", ower=" + ower + ", area=" + area + ", bookCategory=" + bookCategory
				+ ", bookName=" + bookName + ", bookDesc=" + bookDesc + ", bookImg=" + bookImg + ", priority="
				+ priority + ", createTime=" + createTime + ", lastEditTime=" + lastEditTime + ", enableStatus="
				+ enableStatus + ", endStatus=" + endStatus + "]";
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
