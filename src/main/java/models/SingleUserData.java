package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserData {
	private int id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getFirstName() {
		return first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public String getAvthar() {
		return avatar;
	}

}
