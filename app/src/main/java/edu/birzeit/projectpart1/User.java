package edu.birzeit.projectpart1;

public class User {
    private long Id;
    private String email;
    private String password;

    public User() {

    }
    public User(int id, String email, String password) {
        Id = id;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
