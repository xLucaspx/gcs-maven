package br.pucrs.utils;

public class Authenticator {

	public boolean auth(String username, String password) {
		if (username == null || password == null) {
			return false;
		}
		return username.equals("admin")
				&& password.equals("admin");
	}
}
