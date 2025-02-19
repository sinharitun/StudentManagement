/**
 * 
 */
package com.stu_management;
import java.io.BufferedReader;
import java.io.FileReader;

import com.studentDetail.*;

/**
 * 
 */
public class StudentManagementSystem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//to read student data from file using buffer reader
	public static void readStudentData(String fileName) {
		try(BufferedReader br=new BufferedReader(new FileReader("studentData.txt"))){
			String line;
			while((line=br.readline()) != null) {
				String[] data=line.split(",");
				int usn=Integer.parseInt(data[0].trim());
				String name = data[1].trim();
				double cgpa = Double.parseDouble(data[2].trim());
				String email= data[3].trim();
				Student st=new Student(usn,name,cgpa,email);
				st
			}
		}
	}
}
