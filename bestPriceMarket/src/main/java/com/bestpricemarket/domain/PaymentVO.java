package com.bestpricemarket.domain;

public class PaymentVO {
	
	private String pg;
	private int p_g_gno;
	private String p_method;
	private String p_merchant_uid;
	private String p_buyer_name;
	private String p_buyer_tel;
	private String p_g_gname;
	private int p_g_bidprice;
	private String p_re_name;
	private String p_re_tel;
	private String p_re_addr1;
	private String p_re_addr2;
	
	public PaymentVO() {}

	public PaymentVO(String pg, int p_g_gno, String p_method, String p_merchant_uid, String p_buyer_name,
			String p_buyer_tel, String p_g_gname, int p_g_bidprice, String p_re_name, String p_re_tel,
			String p_re_addr1, String p_re_addr2) {
		super();
		this.pg = pg;
		this.p_g_gno = p_g_gno;
		this.p_method = p_method;
		this.p_merchant_uid = p_merchant_uid;
		this.p_buyer_name = p_buyer_name;
		this.p_buyer_tel = p_buyer_tel;
		this.p_g_gname = p_g_gname;
		this.p_g_bidprice = p_g_bidprice;
		this.p_re_name = p_re_name;
		this.p_re_tel = p_re_tel;
		this.p_re_addr1 = p_re_addr1;
		this.p_re_addr2 = p_re_addr2;
	}

	public String getPg() {
		return pg;
	}

	public void setPg(String pg) {
		this.pg = pg;
	}

	public int getP_g_gno() {
		return p_g_gno;
	}

	public void setP_g_gno(int p_g_gno) {
		this.p_g_gno = p_g_gno;
	}

	public String getP_method() {
		return p_method;
	}

	public void setP_method(String p_method) {
		this.p_method = p_method;
	}

	public String getP_merchant_uid() {
		return p_merchant_uid;
	}

	public void setP_merchant_uid(String p_merchant_uid) {
		this.p_merchant_uid = p_merchant_uid;
	}

	public String getP_buyer_name() {
		return p_buyer_name;
	}

	public void setP_buyer_name(String p_buyer_name) {
		this.p_buyer_name = p_buyer_name;
	}

	public String getP_buyer_tel() {
		return p_buyer_tel;
	}

	public void setP_buyer_tel(String p_buyer_tel) {
		this.p_buyer_tel = p_buyer_tel;
	}

	public String getP_g_gname() {
		return p_g_gname;
	}

	public void setP_g_gname(String p_g_gname) {
		this.p_g_gname = p_g_gname;
	}

	public int getP_g_bidprice() {
		return p_g_bidprice;
	}

	public void setP_g_bidprice(int p_g_bidprice) {
		this.p_g_bidprice = p_g_bidprice;
	}

	public String getP_re_name() {
		return p_re_name;
	}

	public void setP_re_name(String p_re_name) {
		this.p_re_name = p_re_name;
	}

	public String getP_re_tel() {
		return p_re_tel;
	}

	public void setP_re_tel(String p_re_tel) {
		this.p_re_tel = p_re_tel;
	}

	public String getP_re_addr1() {
		return p_re_addr1;
	}

	public void setP_re_addr1(String p_re_addr1) {
		this.p_re_addr1 = p_re_addr1;
	}

	public String getP_re_addr2() {
		return p_re_addr2;
	}

	public void setP_re_addr2(String p_re_addr2) {
		this.p_re_addr2 = p_re_addr2;
	}

	@Override
	public String toString() {
		return "PaymentVO [pg=" + pg + ", p_g_gno=" + p_g_gno + ", p_method=" + p_method + ", p_merchant_uid="
				+ p_merchant_uid + ", p_buyer_name=" + p_buyer_name + ", p_buyer_tel=" + p_buyer_tel + ", p_g_gname="
				+ p_g_gname + ", p_g_bidprice=" + p_g_bidprice + ", p_re_name=" + p_re_name + ", p_re_tel=" + p_re_tel
				+ ", p_re_addr1=" + p_re_addr1 + ", p_re_addr2=" + p_re_addr2 + "]";
	}
	
}
