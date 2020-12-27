package com.bestpricemarket.domain;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class FileVO {
	private int fno;
	private int f_g_gno;
	private String f_oname;
	private String f_name;
	private int f_size;
	private Timestamp update_date;
	private String del_chk;
	private Timestamp crea_date;
	private MultipartFile upload; 
	
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getF_g_gno() {
		return f_g_gno;
	}
	public void setF_g_gno(int f_g_gno) {
		this.f_g_gno = f_g_gno;
	}
	public String getF_oname() {
		return f_oname;
	}
	public void setF_oname(String f_oname) {
		this.f_oname = f_oname;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public int getF_size() {
		return f_size;
	}
	public void setF_size(int f_size) {
		this.f_size = f_size;
	}
	public Timestamp getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Timestamp update_date) {
		this.update_date = update_date;
	}
	public String getDel_chk() {
		return del_chk;
	}
	public void setDel_chk(String del_chk) {
		this.del_chk = del_chk;
	}
	public Timestamp getCrea_date() {
		return crea_date;
	}
	public void setCrea_date(Timestamp crea_date) {
		this.crea_date = crea_date;
	}
	
	public FileVO() {}
	public FileVO(int fno, int f_g_gno, String f_oname, String f_name, int f_size, Timestamp update_date,
			String del_chk, Timestamp crea_date) {
		super();
		this.fno = fno;
		this.f_g_gno = f_g_gno;
		this.f_oname = f_oname;
		this.f_name = f_name;
		this.f_size = f_size;
		this.update_date = update_date;
		this.del_chk = del_chk;
		this.crea_date = crea_date;
		
	}
	
	@Override
	public String toString() {
		return "FileVO [fno=" + fno + ", f_g_gno=" + f_g_gno + ", f_oname=" + f_oname + ", f_name=" + f_name
				+ ", f_size=" + f_size + ", update_date=" + update_date + ", del_chk=" + del_chk + ", crea_date="
				+ crea_date + "]";
	}
	
	
	
	
	

}
