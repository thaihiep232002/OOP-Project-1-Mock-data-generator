import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import org.javatuples.*;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		RandomIntegerGenerator _rng = new RandomIntegerGenerator();
		String filenames = "Student=students.txt, RandomStudent=RandomStudents.txt";
		TextDbContext database = new TextDbContext(filenames);
		// Inside has Ecexption
		Quartet<Boolean, Integer, String, ArrayList<Student>> target = database.Students.getAll();
		Quartet<Boolean, Integer, String, ArrayList<Student>> random = database.RandomStudents.getAll();
		
		// Some results are returned from student database 
		Boolean isSuccess = target.getValue0();
		Integer error_code = target.getValue1();
		String message = target.getValue2();
		ArrayList<Student> students = target.getValue3();

		// Some results are returned from random student database
		Boolean r_isSuccess = random.getValue0();
		Integer r_error_code = random.getValue1();
		String r_message = random.getValue2();
		ArrayList<Student> r_all_students = random.getValue3();

		ArrayList<Student> r_N_students = DbSet.getRandomStudents(r_all_students, _rng.next(5, 10));	
	
		
		if (isSuccess && r_isSuccess) {
			database.Students.concat(r_N_students);
			float value = database.Students.getAverageGPA();
		
			// Overwrite from N random student to students.txt
			String random_data = database.RandomStudents.getAllData(); 

			// Inside has Exception :D
			database.Students.overwrite(r_N_students);

			// Get all the Students that have gpa greater than the average GPA
			ArrayList<Student> filter_students = database.Students.findGreater(value); 

			System.out.println("All the students \n");

			for (Student student : students) {
				System.out.println(student.StudentToStringConverter());
			} 
			System.out.println("The Average GPA is " + value);
			
			System.out.println("All the students that have gpa greater than " + value  + "\n");
			for (Student student : filter_students) {
				System.out.println(student.StudentToStringConverter());
			}
		}
		else {
			System.out.println(message);
			System.out.println(r_message);
		}
	}
}
