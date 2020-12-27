package com.bestpricemarket.domain;



public class MyBiddingVO {
	private String gname;
	private String g_m_id;
	private int gno;
	private String pm_m_userid;
	private int actionstatus;
	private String f_name;
	private int pm_g_gno;
	
	public MyBiddingVO() {}
	public MyBiddingVO(String gname, String g_m_id, int gno, String pm_m_userid, int actionstatus, String f_name,
			int pm_g_gno) {
		super();
		this.gname = gname;
		this.g_m_id = g_m_id;
		this.gno = gno;
		this.pm_m_userid = pm_m_userid;
		this.actionstatus = actionstatus;
		this.f_name = f_name;
		this.pm_g_gno = pm_g_gno;
	}


	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public String getG_m_id() {
		return g_m_id;
	}
	public void setG_m_id(String g_m_id) {
		this.g_m_id = g_m_id;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public String getPm_m_userid() {
		return pm_m_userid;
	}
	public void setPm_m_userid(String pm_m_userid) {
		this.pm_m_userid = pm_m_userid;
	}
	public int getActionstatus() {
		return actionstatus;
	}
	public void setActionstatus(int actionstatus) {
		this.actionstatus = actionstatus;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getPm_g_gno() {
		return pm_g_gno;
	}
	public void setPm_g_gno(int pm_g_gno) {
		this.pm_g_gno = pm_g_gno;
	}
	@Override
	public String toString() {
		return "MyBiddingVO [gname=" + gname + ", g_m_id=" + g_m_id + ", gno=" + gno + ", pm_m_userid=" + pm_m_userid
				+ ", actionstatus=" + actionstatus + ", f_name=" + f_name + ", pm_g_gno=" + pm_g_gno + "]";
	}
	
	
	
}
