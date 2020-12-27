package com.bestpricemarket.domain;

public class ReportVO {
	private String g_m_id;
	private String reporter;
	private String reporterEmail;
	private int gno;
	private String gname;
	private int repo;
	
	public ReportVO() {}
	
	public ReportVO(String g_m_id, String reporter, String reporterEmail, int gno, String gname, int repo) {
		super();
		this.g_m_id = g_m_id;
		this.reporter = reporter;
		this.reporterEmail = reporterEmail;
		this.gno = gno;
		this.gname = gname;
		this.repo = repo;
	}

	public String getG_m_id() {
		return g_m_id;
	}

	public void setG_m_id(String g_m_id) {
		this.g_m_id = g_m_id;
	}

	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public int getGno() {
		return gno;
	}

	public void setGno(int gno) {
		this.gno = gno;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public int getRepo() {
		return repo;
	}

	public void setRepo(int repo) {
		this.repo = repo;
	}

	@Override
	public String toString() {
		return "ReportVO [g_m_id=" + g_m_id + ", reporter=" + reporter + ", reporterEmail=" + reporterEmail + ", gno="
				+ gno + ", gname=" + gname + ", repo=" + repo + "]";
	}

}
