import java.util.ArrayList;
import java.util.regex.*;


public class Student {
	private String _id;
	private String _name;
	private String _gpa;
	private String _telephone;
	private String _email;
	private String _dob;
	private String _address;
	


	public Student() {

	}

	public String getGPA() {
		return this._gpa;
	}
	
	public String getId() {
		return this._id;
	}

	public String getName() {
		return this._name;
	}

	public String getTelephone() {
		return this._telephone;
	} 

	public String getEmail() {
		return this._email;
	}

	public String getDOB() {
		return this._dob;
	}

	public String getAddress() {
		return this._address;
	}
	public Student(String id, String name, String gpa, String telephone, String email, String dob, String address) {
		this._id = id;
		this._name = name;
		this._gpa = gpa;
		this._telephone = telephone;
		this._email = email;
		this._dob = dob;
		this._address = address;
	}
	
	public void Setter(String id, String name, String gpa, String telephone, String email, String dob, String address) {
		this._id = id;
		this._name = name;
		this._gpa = gpa;
		this._telephone = telephone;
		this._email = email;
		this._dob = dob;
		this._address = address;
	}

	public boolean isValidID (String id) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher check = pattern.matcher(id);
		boolean result = check.matches();
		return result;
	}

	public boolean isValidName (String name) {
		Pattern pattern = Pattern.compile("(([A-Z])\\w+\\s){1,4}(([A-Z])\\w+\\S)");
		Matcher check = pattern.matcher(name);
		boolean result = check.matches();
		return result;
	}

	public boolean isValidGPA (String gpa) {
		Pattern pattern = Pattern.compile("[0-9]{1,2}(.\\d\\d?)?");
		Matcher check = pattern.matcher(gpa);
		boolean result = check.matches();
		return result;
	}

	public boolean isValidTelephone (String telephone) {
		Pattern pattern = Pattern.compile("0\\d{3}(-\\d{3}){2}");
		Matcher check = pattern.matcher(telephone);
		boolean result = check.matches();
		return result;
	}
	
	public boolean isValidEmail (String email) {
		Pattern pattern = Pattern.compile("\\w+@student.hcmus.edu.vn");
		Matcher check = pattern.matcher(email);
		boolean result = check.matches();
		return result;
	}

	public String StudentToStringConverter() {
		String result = "";
		result = this._id + " - " + this._name + ", GPA:" + this._gpa;
		return result;
	}
}
