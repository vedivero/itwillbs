package com.bestpricemarket.domain;

import java.sql.Timestamp;

public class PricemonitoringVO {
	private int pmno;
	private int pm_g_gno;
	private String pm_m_userid;
	private int pm_g_bidprice;
	private Timestamp timelog;
	
	public PricemonitoringVO() {}

	public PricemonitoringVO(int pmno, int pm_g_gno, String pm_m_userid, int pm_g_bidprice, Timestamp timelog) {
		super();
		this.pmno = pmno;
		this.pm_g_gno = pm_g_gno;
		this.pm_m_userid = pm_m_userid;
		this.pm_g_bidprice = pm_g_bidprice;
		this.timelog = timelog;
	}

	public int getPmno() {
		return pmno;
	}

	public void setPmno(int pmno) {
		this.pmno = pmno;
	}

	public int getPm_g_gno() {
		return pm_g_gno;
	}

	public void setPm_g_gno(int pm_g_gno) {
		this.pm_g_gno = pm_g_gno;
	}

	public String getPm_m_userid() {
		return pm_m_userid;
	}

	public void setPm_m_userid(String pm_m_userid) {
		this.pm_m_userid = pm_m_userid;
	}

	public int getPm_g_bidprice() {
		return pm_g_bidprice;
	}

	public void setPm_g_bidprice(int pm_g_bidprice) {
		this.pm_g_bidprice = pm_g_bidprice;
	}

	public Timestamp getTimelog() {
		return timelog;
	}

	public void setTimelog(Timestamp timelog) {
		this.timelog = timelog;
	}

	@Override
	public String toString() {
		return "PricemonitoringVO [pmno=" + pmno + ", pm_g_gno=" + pm_g_gno + ", pm_m_userid=" + pm_m_userid
				+ ", pm_g_bidprice=" + pm_g_bidprice + ", timelog=" + timelog + "]";
	}
	
	
}
