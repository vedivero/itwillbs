package com.bestpricemarket.domain;

	import java.util.Date;

public class finalBidVO {
		private String pm_m_userid;
		private int pm_g_gno;
		private Date endDate;
		
		public finalBidVO() {}

		public finalBidVO(String pm_m_userid, int pm_g_gno, Date endDate) {
			super();
			this.pm_m_userid = pm_m_userid;
			this.pm_g_gno = pm_g_gno;
			this.endDate = endDate;
		}

		public String getPm_m_userid() {
			return pm_m_userid;
		}

		public void setPm_m_userid(String pm_m_userid) {
			this.pm_m_userid = pm_m_userid;
		}

		public int getPm_g_gno() {
			return pm_g_gno;
		}

		public void setPm_g_gno(int pm_g_gno) {
			this.pm_g_gno = pm_g_gno;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		@Override
		public String toString() {
			return "finalBidVO [pm_m_userid=" + pm_m_userid + ", pm_g_gno=" + pm_g_gno + ", endDate=" + endDate + "]";
		}	
	}


