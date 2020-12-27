package com.bestpricemarket.domain;

public class LikesVO {
	
	private int lno;
	private int l_g_gno;
	private String l_m_id;
	private int l_m_actionstatus;
	
	
	public LikesVO() {}
	
	
	public int getLno() {
		return lno;
	}
	public void setLno(int lno) {
		this.lno = lno;
	}
	public int getL_g_gno() {
		return l_g_gno;
	}
	public void setL_g_gno(int l_g_gno) {
		this.l_g_gno = l_g_gno;
	}
	public String getL_m_id() {
		return l_m_id;
	}
	public void setL_m_id(String l_m_id) {
		this.l_m_id = l_m_id;
	}
	public int getL_m_actionstatus() {
		return l_m_actionstatus;
	}
	public void setL_m_actionstatus(int l_m_actionstatus) {
		this.l_m_actionstatus = l_m_actionstatus;
	}
	
	
	
	@Override
	public String toString() {
		return "likesVO [lno=" + lno + ", l_g_gno=" + l_g_gno + ", l_m_id=" + l_m_id + ", l_m_actionstatus="
				+ l_m_actionstatus + "]";
	}
	
	
	
	
	

}
