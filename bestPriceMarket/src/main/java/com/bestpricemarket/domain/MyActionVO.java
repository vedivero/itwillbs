package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class MyActionVO {

 private int ano;
 private String a_m_id;
 private int a_g_gno;
 private Integer a_g_actionstatus;
 private int paystatus;
 private int deliverystatus;
 private Timestamp a_g_enddate;
 private int sellstatus;
 private String f_name;
 private String gname;
 
 
 public MyActionVO() {}


public MyActionVO(int ano, String a_m_id, int a_g_gno, Integer a_g_actionstatus, int paystatus, int deliverystatus,
		Timestamp a_g_enddate, int sellstatus, String f_name, String gname) {
	super();
	this.ano = ano;
	this.a_m_id = a_m_id;
	this.a_g_gno = a_g_gno;
	this.a_g_actionstatus = a_g_actionstatus;
	this.paystatus = paystatus;
	this.deliverystatus = deliverystatus;
	this.a_g_enddate = a_g_enddate;
	this.sellstatus = sellstatus;
	this.f_name = f_name;
	this.gname = gname;
}


public int getAno() {
	return ano;
}


public void setAno(int ano) {
	this.ano = ano;
}


public String getA_m_id() {
	return a_m_id;
}


public void setA_m_id(String a_m_id) {
	this.a_m_id = a_m_id;
}


public int getA_g_gno() {
	return a_g_gno;
}


public void setA_g_gno(int a_g_gno) {
	this.a_g_gno = a_g_gno;
}


public Integer getA_g_actionstatus() {
	return a_g_actionstatus;
}


public void setA_g_actionstatus(Integer a_g_actionstatus) {
	this.a_g_actionstatus = a_g_actionstatus;
}


public int getPaystatus() {
	return paystatus;
}


public void setPaystatus(int paystatus) {
	this.paystatus = paystatus;
}


public int getDeliverystatus() {
	return deliverystatus;
}


public void setDeliverystatus(int deliverystatus) {
	this.deliverystatus = deliverystatus;
}


public Timestamp getA_g_enddate() {
	return a_g_enddate;
}


public void setA_g_enddate(Timestamp a_g_enddate) {
	this.a_g_enddate = a_g_enddate;
}


public int getSellstatus() {
	return sellstatus;
}


public void setSellstatus(int sellstatus) {
	this.sellstatus = sellstatus;
}


public String getF_name() {
	return f_name;
}


public void setF_name(String f_name) {
	this.f_name = f_name;
}


public String getGname() {
	return gname;
}


public void setGname(String gname) {
	this.gname = gname;
}


@Override
public String toString() {
	return "MyActionVO [ano=" + ano + ", a_m_id=" + a_m_id + ", a_g_gno=" + a_g_gno + ", a_g_actionstatus="
			+ a_g_actionstatus + ", paystatus=" + paystatus + ", deliverystatus=" + deliverystatus + ", a_g_enddate="
			+ a_g_enddate + ", sellstatus=" + sellstatus + ", f_name=" + f_name + ", gname=" + gname + "]";
}

 

 
 
	
 
}
