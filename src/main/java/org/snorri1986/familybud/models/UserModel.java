package org.snorri1986.familybud.models;

public class UserModel {
  String username;
  String password;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String toString() {
    return "UserModel{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}
