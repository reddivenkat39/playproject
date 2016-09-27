package model;

import org.hibernate.annotations.Entity;

@Entity
public class UserTokens {
public String username;
public String token;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}

}
