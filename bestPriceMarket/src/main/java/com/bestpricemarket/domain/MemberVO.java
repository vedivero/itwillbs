package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class MemberVO {
	private String id;
	private String pw;
	private String username;
	private String email;
	private String phone;
	private String addr1;
	private String addr2;
	private Timestamp reg_date;
	private int block;
	private String block_r;
	private int score;
	
	//생성자 2개 : 기본생성자, 멤버변수를 인자로 전부받는 생성자
	public MemberVO() {}
	public MemberVO(String id, String pw, String username, String email, String phone, String addr1, String addr2,
			Timestamp reg_date, int block, String block_r, int score) {
		super();
		this.id = id;
		this.pw = pw;
		this.username = username;
		this.email = email;
		this.phone = phone;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.reg_date = reg_date;
		this.block = block;
		this.block_r = block_r;
		this.score = score;
	}

	//get() set()메서드 생성
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = block;
	}
	public String getBlock_r() {
		return block_r;
	}
	public void setBlock_r(String block_r) {
		this.block_r = block_r;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", username=" + username + ", email=" + email + ", phone=" + phone
				+ ", addr1=" + addr1 + ", addr2=" + addr2 + ", reg_date=" + reg_date + ", block=" + block + ", block_r="
				+ block_r + ", score=" + score + "]";
	}
}
