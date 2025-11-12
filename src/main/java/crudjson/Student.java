package crudjson;

public class Student {
	public int id;
	public String name;
	public int age;
	
	// empty constructor needed for JSON (de)serialization
	public Student() {
		
	}
	
	public Student(int id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
}
