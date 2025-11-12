package crudjson;

import java.util.ArrayList;
import java.util.Scanner;

// note: wasn't sure how to break up this part into multiple methods

public class StudentsUI {
	private static boolean mainLoop(StudentsManager studentsManager, Scanner sc) {
		System.out.println("""
        === STUDENT MANAGEMENT SYSTEM === 
        1. Add Student 
        2. View Students 
        3. Update Student 
        4. Delete Student 
        5. Exit""");
        System.out.print("Enter choice: ");
        int userChoice = sc.nextInt();
        switch (userChoice) {
            case 1:
                System.out.print("Enter name: ");
                String name = sc.next();
                System.out.print("Enter age: ");
                int age = sc.nextInt();
                
                studentsManager.addStudent(name, age);
                
                System.out.println("Student added successfully!");
                break;
            case 2:
            	System.out.println("--- Student List ---");
            	ArrayList<Student> students = studentsManager.getAllStudents();
            	int size = students.size();
            	for (int i = 0; i < size; i++)
            	System.out.printf("Student{ID=%d, Name='%s', Age=%d}\n",
            			students.get(i).id,
            			students.get(i).name,
            			students.get(i).age);
            	break;
            case 3:
            	System.out.print("Enter id of student to update: ");
            	int updateId = sc.nextInt();

                System.out.print("Enter new name: ");
                String updateName = sc.next();
                System.out.print("Enter new age: ");
                int updateAge = sc.nextInt();
                
                Student updatedStudent = studentsManager
                		.updateStudent(updateId, updateName, updateAge);

            	if (updatedStudent != null) {
            		System.out.println("Student updated");
            	} else {
            		System.out.println("Student not found with the entered id");
            	}
            	break;
            case 4:
            	System.out.print("Enter id of student to delete: ");
            	int deleteId = sc.nextInt();
            	boolean deleted = studentsManager.deleteStudent(deleteId);
            	if (deleted) {
            		System.out.println("Student deleted");
            	} else {
            		System.out.println("Student not found with the entered id");
            	}
            	break;
            case 5:
            	studentsManager.saveData();
                System.out.println("Bye!");
                return true;
            default:
                break;
        }
        System.out.println("");
        return false;
	}
	
	public static void start() {
	    boolean exit = false;
        StudentsManager studentsManager = new StudentsManager("students.json");
	    Scanner sc = new Scanner(System.in);
	    
	    while (!exit) {
            exit = StudentsUI.mainLoop(studentsManager, sc);
        }
        sc.close();
	}
}
