package cr.ac.itcr.examen1.entitys;

/**
 * Created by Laurens on 11/04/2016.
 */
public class User {


    private int Id;
    private String Name;
    private String Email;
    private String Password;


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
