package com.bestpricemarket.domain;

public class ParamVO {
	private String imp_uid;
	private int ano;
	private String test;
	
	public ParamVO() {}

	public ParamVO(String imp_uid, int ano, String test) {
		super();
		this.imp_uid = imp_uid;
		this.ano = ano;
		this.test = test;
	}
	
	public String getImp_uid() {
		return imp_uid;
	}

	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	@Override
	public String toString() {
		return "ParamVO [imp_uid=" + imp_uid + ", ano=" + ano + ", test=" + test + "]";
	}

	
	
}
