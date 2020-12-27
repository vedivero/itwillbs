package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class CSVO {

	private int csbno;
	private String cs_m_id;
	private String cs_subject;
	private String cs_content;
	private int cs_ref;
	private int cs_seq;
	private int cs_lev;
	private Timestamp cs_regdate;
	private String cs_file;
	
	public CSVO() {}
	public CSVO(int csbno, String cs_m_id, String cs_subject, String cs_content, int cs_ref, int cs_seq, int cs_lev,
			Timestamp cs_regdate, String cs_file) {
		super();
		this.csbno = csbno;
		this.cs_m_id = cs_m_id;
		this.cs_subject = cs_subject;
		this.cs_content = cs_content;
		this.cs_ref = cs_ref;
		this.cs_seq = cs_seq;
		this.cs_lev = cs_lev;
		this.cs_regdate = cs_regdate;
		this.cs_file = cs_file;
	}
	public int getCsbno() {
		return csbno;
	}
	public void setCsbno(int csbno) {
		this.csbno = csbno;
	}
	public String getCs_m_id() {
		return cs_m_id;
	}
	public void setCs_m_id(String cs_m_id) {
		this.cs_m_id = cs_m_id;
	}
	public String getCs_subject() {
		return cs_subject;
	}
	public void setCs_subject(String cs_subject) {
		this.cs_subject = cs_subject;
	}
	public String getCs_content() {
		return cs_content;
	}
	public void setCs_content(String cs_content) {
		this.cs_content = cs_content;
	}
	public int getCs_ref() {
		return cs_ref;
	}
	public void setCs_ref(int cs_ref) {
		this.cs_ref = cs_ref;
	}
	public int getCs_seq() {
		return cs_seq;
	}
	public void setCs_seq(int cs_seq) {
		this.cs_seq = cs_seq;
	}
	public int getCs_lev() {
		return cs_lev;
	}
	public void setCs_lev(int cs_lev) {
		this.cs_lev = cs_lev;
	}
	public Timestamp getCs_regdate() {
		return cs_regdate;
	}
	public void setCs_regdate(Timestamp cs_regdate) {
		this.cs_regdate = cs_regdate;
	}
	public String getCs_file() {
		return cs_file;
	}
	public void setCs_file(String cs_file) {
		this.cs_file = cs_file;
	}
	
	@Override
	public String toString() {
		return "CSVO [csbno=" + csbno + ", cs_m_id=" + cs_m_id + ", cs_subject=" + cs_subject + ", cs_content="
				+ cs_content + ", cs_ref=" + cs_ref + ", cs_seq=" + cs_seq + ", cs_lev=" + cs_lev + ", cs_regdate="
				+ cs_regdate + ", cs_file=" + cs_file + "]";
	}
	
	
	
	
}
