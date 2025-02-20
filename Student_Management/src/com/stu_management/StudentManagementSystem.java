/**
 * 
 */
package com.stu_management;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import com.studentDetail.*;

/**
 * 
 */
public class StudentManagementSystem {

	/**
	 * @param args
	 */
	private static List<Student> students=new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName="studentData.txt";
		readStudentData(fileName);
		
		Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("----- Student Management System -----");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students by Name (Ascending)");
            System.out.println("5. Display Students by GPA (Descending)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    updateFile(fileName);
                    break;
                case 2:
                    removeStudent(scanner);
                    updateFile(fileName);
                    break;
                case 3:
                    updateStudent(scanner);
                    updateFile(fileName);
                    break;
                case 4:
                    displayStudentsByName();
                    break;
                case 5:
                    displayStudentsByGPA();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Thank you for using the Student Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
	}
	
	//to read student data from file using buffer reader
	public static void readStudentData(String fileName) {
		
		File file = new File(fileName);

	    // Check if file exists, if not, create it
	    if (!file.exists()) {
	        try {
	            if (file.createNewFile()) {
	                System.out.println("File not found! A new file '" + fileName + "' has been created.");
	            }
	        } catch (IOException e) {
	            System.out.println("Error creating file: " + e.getMessage());
	            return;
	        }
	    }
		
		try(BufferedReader br=new BufferedReader(new FileReader(fileName))){
			String line;
			while((line=br.readLine()) != null) {
				String[] data=line.split(",");
				if (data.length < 3) continue;
				int usn=Integer.parseInt(data[0].trim());
				String name = data[1].trim();
				double cgpa = Double.parseDouble(data[2].trim());
				String email= data[3].trim();
				Student st=new Student(usn,name,cgpa,email);
				students.add(st);
			}
			
		}
		catch(IOException e) {
			System.out.println("An error occurred while reading the student data: " + e.getMessage());
		}catch (NumberFormatException e) {
            System.out.println("Invalid number format in the student data: " + e.getMessage());
        }
	}
	
	
	public static void updateFile(String fileName) {
		try( FileWriter writer =new FileWriter(fileName) ){
			for(Student student : students) {
				writer.write(student.getusn() + "," + student.getname() + "," + student.getcgpa() + "," + student.getemail() + System.lineSeparator());
			}
			System.out.println("Student data updated successfully");
		}
		catch(IOException e) {
			System.out.println("Error in updating data to file" + e.getMessage());
		}
	}
	
	public static void addStudent(Scanner scanner) {
		System.out.println("***** Add New Student Detail *****");
		try {
			System.out.println("Enter USN of Student");
			int usn= scanner.nextInt();
			scanner.nextLine();
			System.out.println("Enter Student Name");
			String name=scanner.nextLine();
			System.out.println("Enter CGPA of Student");
			double cgpa=scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter Email ID of Student");
			String email=scanner.nextLine();
			
			Student student = new Student(usn,name,cgpa,email);
			students.add(student);
		}catch(InputMismatchException e) {
			System.out.println("Enter data in correct format" + e.getMessage());
		}
		
		System.out.println("Student added successfully.");
	}
	
	public static void removeStudent(Scanner scanner) {
		System.out.println("***** Remove Student Detail *****");
		System.out.println("Enter USN of Student to delete");
		int usn=scanner.nextInt();
		boolean found=false;
		
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student student=iterator.next();
			if(student.getusn()==usn) {
				iterator.remove();
				found= true;
				break;
			}
		}
		if (found) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
	}
	
	public static void updateStudent(Scanner scanner) {
		System.out.println("***** Update Student Detail *****");
		System.out.println("Enter USN of Student to update details");
		int usn=scanner.nextInt();
		boolean found=false;
		for(Student student : students) {
			if(student.getusn() == usn) {
				scanner.nextLine();
				System.out.print("Enter name( press Enter to skip)");
				String name=scanner.nextLine();
				if (!name.isEmpty()) {
	                student.setName(name);
	            }
				System.out.print("Enter CGPA ( press Enter to skip)");
				String cgpaInput = scanner.nextLine();
				if (!cgpaInput.isEmpty()) {
				    student.setcgpa(Double.parseDouble(cgpaInput));
				}
				scanner.nextLine();
				System.out.print("Enter Email ID ( press Enter to skip)");
				String email=scanner.nextLine();
				if (!email.isEmpty()) {
	                student.setemail(email);
	            }
				found=true;
				break;
			}
		}
		if (found) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
	}
	
	
	public static void displayStudentsByName() {
        System.out.println("----- Students by Name (Ascending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparing(Student::getname));
        System.out.println("USN\tName\t\tCGPA\tEmail");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudentsByGPA() {
        System.out.println("----- Students by GPA (Descending) -----");
        List<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, Comparator.comparingDouble(Student::getcgpa).reversed());
        System.out.println("USN\tName\t\tCGPA\tEmail");
        System.out.println("******************************************");
        displayStudents(sortedStudents);
    }

    public static void displayStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student+ "\n\n");
        }
    }
}
