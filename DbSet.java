import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import org.javatuples.*;

public class DbSet {
	private String _filename;
	private ArrayList<Student> _students;
	private String _filebuffer;

	public DbSet() {
		this._filebuffer = "";
	}

	public void Setter(String filename) {
		this._filename = filename;
	}
	
	public DbSet(String filename) {
		this._filename = filename;
		this._filebuffer = "";
	}
	
	public String getAllData() {
		return _filebuffer;
	}
	
	public float getAverageGPA() {
		float result;
		float sum = 0;
		int cnt = 0;
		for (Student student : this._students) {
			sum	+= Float.parseFloat(student.getGPA());
			cnt++;
		}
		result = sum / cnt;
		return result;
	}

	// get all students in data file
	public Quartet<Boolean, Integer, String, ArrayList<Student>> getAll() {
		Quartet<Boolean, Integer, String, ArrayList<Student>> result;
		ArrayList<Student> target = new ArrayList<Student>();
		String buffer = "";
		try {
			File fin = new File(this._filename);
			Scanner reader = new Scanner(fin);
			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				buffer += data;
				this._filebuffer += data + "\n";
			}
			reader.close();
		}	
		catch (IOException e) {
			System.out.println("Read " + _filename +" File Error!");
		}	

		String[] items = buffer.split("Student: ");
		
		// buffer value
		String id, name, gpa, telephone, email, dob, address; 
		for (String item : items) {
			Student temp = new Student();
			if (item != "") {
				String[] allDatas = item.split("    ");
				String[] IdAndNameDatas = allDatas[0].split(" - ");// example: ['20127496', 'Nguyen Thai Hiep']
				id = IdAndNameDatas[0];
				name = IdAndNameDatas[1];	
				String[] GpaAndTelephone = allDatas[1].split(", "); // example: ['GPA=7.30', 'Telephone=0909-888-773']
				gpa = (GpaAndTelephone[0].split("="))[1]; // example: GPA=7.30
				telephone = (GpaAndTelephone[1].split("="))[1]; // example: Telephone=0909-888-773
				email = (allDatas[2].split("="))[1];
				dob = (allDatas[3].split("="))[1];
				address = (allDatas[4].split("="))[1];
				// checking
				if (!temp.isValidID(id)) {
					result = Quartet.with(false, 1, "ID Format Error", target);
					return result;
				}
				if (!temp.isValidName(name)) {
					result = Quartet.with(false, 1, "Name Format Error", target);
					return result;
				}
				if (!temp.isValidGPA(gpa)) {
					result = Quartet.with(false, 1, "GPA Format Error", target);
					return result;
				}
				if (!temp.isValidTelephone(telephone)) {
					result = Quartet.with(false, 1, "Telephone Format Error", target);
					return result;
				}
				if (!temp.isValidEmail(email)) {
					result = Quartet.with(false, 1, "Email Format Error", target);
					return result;
				}
				target.add(new Student(id, name, gpa, telephone, email, dob, address));
			}
		}	
		this._students = target;
		result = Quartet.with(true, 0, "", target);
		return result;
	}

	public void concat(ArrayList<Student> other) {
		for (Student student : other) {
			this._students.add(student);
		}
	}
	
	public void overwrite(ArrayList<Student> students) {
		File file = new File(this._filename);
		try {	
			FileWriter fout = new FileWriter(file, true);
			for (Student student : students) {
				String buffer = "\n";
				buffer += "Student: " + student.getId() + " - " + student.getName() + "\n    GPA=" + student.getGPA() + ", Telephone=" + student.getTelephone() + "\n    Email=" + student.getEmail() + "\n    DOB=" + student.getDOB() + "\n    Address=" + student.getAddress();
				fout.write(buffer);
			}
			fout.close();
		}
		catch (IOException e) {
			System.out.println("this file is not exists!");
		}
	}

	public ArrayList<Student> findGreater(float average) {
		ArrayList<Student> result = new ArrayList<Student>();
		for (Student student : this._students) {
			String gpa = student.getGPA();	
			float val = Float.parseFloat(gpa);
			if (val >= average) {
				result.add(student);
			}
		}
		return result;	
	}

	public static ArrayList<Student> getRandomStudents(ArrayList<Student> temp, int n) {
		RandomIntegerGenerator _rng = new RandomIntegerGenerator();
		ArrayList<Student> result = new ArrayList<Student>();
		if (n > temp.size()) {
			return result;	
		}
		int[] check_no_repeat = new int[n];
		for (int i = 0; i < n; i++) {
			check_no_repeat[i] = -1;
		}
		for (int i = 0; i < n; i++) {
			int random = _rng.next(n);	
			for (int j = 0; j < n; j++) {
				if (random == check_no_repeat[j]) {
					i--;
					break;
				}
			}
			check_no_repeat[i] = random;
		}

		for (int i = 0; i < n; i++) {
			result.add(temp.get(i));
		}
		return result;
	}
}
