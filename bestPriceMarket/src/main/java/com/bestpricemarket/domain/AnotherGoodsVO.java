package com.bestpricemarket.domain;

public class AnotherGoodsVO {

	private int gno;
	private String g_m_id;
	private String gname;
	private String f_name;
	
	
	public AnotherGoodsVO() {}
	public AnotherGoodsVO(String g_m_id, String gname, String f_name, int gno) {
		super();
		this.g_m_id = g_m_id;
		this.gname = gname;
		this.f_name = f_name;
		this.gno = gno;
	}
	
	
	public String getG_m_id() {
		return g_m_id;
	}
	public void setG_m_id(String g_m_id) {
		this.g_m_id = g_m_id;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	
	@Override
	public String toString() {
		return "AnotherGoodsVO [gno=" + gno + ", g_m_id=" + g_m_id + ", gname=" + gname + ", f_name=" + f_name + "]";
	}
	

	
	
	
}