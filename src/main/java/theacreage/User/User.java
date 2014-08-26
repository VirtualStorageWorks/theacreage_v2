package theacreage.User;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by wptrs on 8/26/2014.
 */
@Entity
@Table(name="user")
public class User {

    public User(){}

    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String password;

    private String email;

    private boolean enabled;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
