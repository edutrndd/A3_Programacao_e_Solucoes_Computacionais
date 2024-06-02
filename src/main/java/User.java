package main.java;

public class User {
    private int id;
    private String name;
    private String email;
    private String cpf;
    private int age;
    private String gender;
    private String country;
    private String password;
    private boolean isAdmin;

    public User(int id, String name, String email, String cpf, int age, String gender, String country, String password, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.age = age;
        this.gender = gender;
        this.country = country;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    // Getters e setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public Object getLogin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLogin'");
    }
}
