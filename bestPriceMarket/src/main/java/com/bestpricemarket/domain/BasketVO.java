package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class BasketVO {
 private Integer lno;
 private int l_g_gno;
 private String l_m_id;
 private int l_m_actionstatus;
 private String gname;
 private int lowestprice;
 private Timestamp enddate;
 private int actionstatus;
 private int gno;
 private String f_name;
 
 
 
 
 
 public BasketVO() {}

public BasketVO(Integer lno, int l_g_gno, String l_m_id, int l_m_actionstatus, String gname, int lowestprice,
		Timestamp enddate, int actionstatus, int gno, String f_name) {
	super();
	this.lno = lno;
	this.l_g_gno = l_g_gno;
	this.l_m_id = l_m_id;
	this.l_m_actionstatus = l_m_actionstatus;
	this.gname = gname;
	this.lowestprice = lowestprice;
	this.enddate = enddate;
	this.actionstatus = actionstatus;
	this.gno = gno;
	this.f_name = f_name;
}

public Integer getLno() {
	return lno;
}

public void setLno(Integer lno) {
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

public String getGname() {
	return gname;
}

public void setGname(String gname) {
	this.gname = gname;
}

public int getLowestprice() {
	return lowestprice;
}

public void setLowestprice(int lowestprice) {
	this.lowestprice = lowestprice;
}

public Timestamp getEnddate() {
	return enddate;
}

public void setEnddate(Timestamp enddate) {
	this.enddate = enddate;
}

public int getActionstatus() {
	return actionstatus;
}

public void setActionstatus(int actionstatus) {
	this.actionstatus = actionstatus;
}

public int getGno() {
	return gno;
}

public void setGno(int gno) {
	this.gno = gno;
}

public String getF_name() {
	return f_name;
}

public void setF_name(String f_name) {
	this.f_name = f_name;
}

@Override
public String toString() {
	return "BasketVO [lno=" + lno + ", l_g_gno=" + l_g_gno + ", l_m_id=" + l_m_id + ", l_m_actionstatus="
			+ l_m_actionstatus + ", gname=" + gname + ", lowestprice=" + lowestprice + ", enddate=" + enddate
			+ ", actionstatus=" + actionstatus + ", gno=" + gno + ", f_name=" + f_name + "]";
}
}

