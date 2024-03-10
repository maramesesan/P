package model;

/**
 * The Client class represents a client with an ID, name, age, password, and email.
 */
public class Client {
     private int id;
     private String name;
     private int age;
     private String password;
     private String email;

    public Client() {
    }

    /**
     * Returns the ID of the client.
     *
     * @return The ID.
     */
    public int isId() {
        return id;
    }


    public String isName() {
        return name;
    }

    public int isAge() {
        return age;
    }

    public String isPassword() {
        return password;
    }

    public String isEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
