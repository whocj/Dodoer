package com.summer.whm.spider.distributed.examples.bean;

import java.io.Serializable;

public class EmpBean implements Serializable {

	private String empName;
	private int empAge;

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	@Override
	public String toString() {
		return "EmpBean [empName=" + empName + ", empAge=" + empAge + "]";
	}
	
	

}
