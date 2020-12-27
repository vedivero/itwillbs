package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class GoodsCommentVO {
	
	private int c_num;
	private int c_g_gno; 
	private String c_m_id;
	private Timestamp c_regdate;
	private String c_content;
	private int c_ref;
	private int c_seq;
	private int c_lev;
	private int isDeleted;
	
	public GoodsCommentVO() {}
	public GoodsCommentVO(int c_num, int c_g_gno, String c_m_id, Timestamp c_regdate, String c_content, int c_ref,
			int c_seq, int c_lev, int isDeleted) {
		super();
		this.c_num = c_num;
		this.c_g_gno = c_g_gno;
		this.c_m_id = c_m_id;
		this.c_regdate = c_regdate;
		this.c_content = c_content;
		this.c_ref = c_ref;
		this.c_seq = c_seq;
		this.c_lev = c_lev;
		this.isDeleted = isDeleted;
	}


	public int getC_num() {
		return c_num;
	}

	public void setC_num(int c_num) {
		this.c_num = c_num;
	}

	public int getC_g_gno() {
		return c_g_gno;
	}

	public void setC_g_gno(int c_g_gno) {
		this.c_g_gno = c_g_gno;
	}

	public String getC_m_id() {
		return c_m_id;
	}

	public void setC_m_id(String c_m_id) {
		this.c_m_id = c_m_id;
	}

	public Timestamp getC_regdate() {
		return c_regdate;
	}

	public void setC_regdate(Timestamp c_regdate) {
		this.c_regdate = c_regdate;
	}

	public String getC_content() {
		return c_content;
	}

	public void setC_content(String c_content) {
		this.c_content = c_content;
	}

	public int getC_ref() {
		return c_ref;
	}

	public void setC_ref(int c_ref) {
		this.c_ref = c_ref;
	}

	public int getC_seq() {
		return c_seq;
	}

	public void setC_seq(int c_seq) {
		this.c_seq = c_seq;
	}

	public int getC_lev() {
		return c_lev;
	}

	public void setC_lev(int c_lev) {
		this.c_lev = c_lev;
	}

	public int getIsDeleted() {
		return isDeleted;
	}
	
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
	public String toString() {
		return "GoodsCommentVO [c_num=" + c_num + ", c_g_gno=" + c_g_gno + ", c_m_id=" + c_m_id + ", c_regdate="
				+ c_regdate + ", c_content=" + c_content + ", c_ref=" + c_ref + ", c_seq=" + c_seq + ", c_lev=" + c_lev
				+ ", isDeleted=" + isDeleted + "]";
	}
}