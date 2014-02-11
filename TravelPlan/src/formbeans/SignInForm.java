package formbeans;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SignInForm extends FormBean {
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public void setUserName(String s) {
		userName = trimAndConvert(s, "<>\"");
	}

	public void setPassword(String s) {
		password = trimAndConvert(s, "<>\"");
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userName == null || userName.length() == 0) {
			errors.add("User Name is required");
		}
		if (userName.matches(".*[<>\"].*")) {
			errors.add("Username may not contain angle brackets or quotes");
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		if (password.matches(".*[<>\"].*")) {
			errors.add("Password may not contain angle brackets or quotes");
		}
		return errors;
	}
}