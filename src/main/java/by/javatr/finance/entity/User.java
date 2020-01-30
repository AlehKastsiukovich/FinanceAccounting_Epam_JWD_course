package by.javatr.finance.entity;

import by.javatr.finance.entity.validator.UserValidator;

public class User implements Comparable<User> {
	private static int idCounter = 0;
	private Integer id;
	private String login;
	private String password;
	private String email;

	private User() {

	}

	private User(UserBuilder builder) {
		login = builder.login;
		password = builder.password;
		email = builder.email;
		id = builder.id;
	}

	public static class UserBuilder {
		private String login;
		private String password;
		private String email;
		private int id;

		public UserBuilder buildLogin(String login) {
			if (!UserValidator.loginIsValid(login)) {
				throw new IllegalArgumentException("Wrong login. Read the  login creating rules!");
			}
			
			this.login = login;

			return this;
		}

		public UserBuilder buildPassword(String password) {
			if (UserValidator.passwordIsValid(password)) {
				throw new IllegalArgumentException("Wrong password. Read the password creating rules!");
			}
			
			this.password = password;

			return this;
		}

		public UserBuilder buildEmail(String email) {
			if (UserValidator.passwordIsValid(email)) {
				throw new IllegalArgumentException("Wrong email. Read the email creating rules!");
			}
			
			this.email = email;

			return this;
		}

		public User build() {
			id = idCounter++;
			return new User(this);
		}
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public void setLogin(String login) {
		if (!UserValidator.loginIsValid(login)) {
			throw new IllegalArgumentException("Wrong login. Read the  login creating rules!");
		}
		
		this.login = login;
	}

	public void setPassword(String password) {
		if (UserValidator.passwordIsValid(password)) {
			throw new IllegalArgumentException("Wrong password. Read the password creating rules!");
		}
		
		this.password = password;
	}

	public void setEmail(String email) {
		if (UserValidator.passwordIsValid(email)) {
			throw new IllegalArgumentException("Wrong email. Read the email creating rules!");
		}
		
		this.email = email;
	}
	
	@Override
	public boolean equals(Object userObject) {
		if (this == userObject) 
			return true;
		if (userObject == null) 
			return false;
		if (this.getClass() != userObject.getClass())
			return false;
		User otherUser = (User) userObject;
		if (this.id != otherUser.id && this.login != otherUser.login 
				&& this.password != otherUser.password && this.email != otherUser.email)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * hash + (int) id;
		hash = 31 * hash + (login == null ? 0 : login.hashCode());
		hash = 31 * hash + (password == null ? 0 : password.hashCode());
		return hash;
	}
	
	@Override
	public String toString() {
		return id + " /" + login + " /" + password + " /" + " /" + email;
	}


	public int compareTo(User o) {
		return id.compareTo(o.id);
	}
}
