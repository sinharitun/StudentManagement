package com.studentDetail;

public class Student {
	
	private int usn;
	private String name;
	private double cgpa;
	private String email;
	
	public Student(int usn,String name, double cgpa, String email) {
		this.usn=usn;
		this.name=name;
		this.cgpa=cgpa;
		this.email=email;
	}
	public int getusn() {
		return usn;
	}
	public void setusn(int usn) {
		this.usn=usn;
	}
	public String getname() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public double getcgpa() {
		return cgpa;
	}
	public void setcgpa(double cgpa) {
		this.cgpa=cgpa;
	}
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email=email;
	}
	public String toString() {
		return usn 
				+ "\t" + name +
				"\t" + cgpa 
				+ "\t" + email;
		
	}
}
