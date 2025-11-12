package crudjson;
import java.util.ArrayList;

import tools.jackson.core.type.TypeReference;

public class StudentsManager {
	private JsonFileManager<ArrayList<Student>> dataManager;
	private String filename;
	private ArrayList<Student> students;

	private ArrayList<Student> loadData() {
		ArrayList<Student> studentsContent = this.dataManager.read();
		return studentsContent == null ? new ArrayList<Student>() : studentsContent;
	}

	StudentsManager(String filename) {
		this.filename = filename;
		this.dataManager = new JsonFileManager<ArrayList<Student>>(
				this.filename,
				new TypeReference<ArrayList<Student>>() {});
		this.students = loadData();
	}
	
	public Student addStudent(String name, int age) {
		int studentsSize = this.students.size();
		int lastStudentId = studentsSize > 0 ? this.students.get(studentsSize - 1).id : 0;
		Student student = new Student(lastStudentId + 1, name, age);
		this.students.add(student);
		return student;
	}
	
	public ArrayList<Student> getAllStudents() {
		return this.students;
	}

	public Student updateStudent(int id, String name, int age) {
		Student foundStudent = this.students.stream()
				.filter(student -> student.id == id)
				.findFirst()
				.orElse(null);
		if (foundStudent == null)
			return null;
		foundStudent.name = name;
		foundStudent.age = age;
		return foundStudent;
	}
	
	public boolean deleteStudent(int id) {
		return this.students.removeIf(student -> student.id == id);
	}
	
	public void saveData() {
		dataManager.write(this.students);
	}
}
