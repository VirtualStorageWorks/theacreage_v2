package theacreage.User;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import theacreage.BusinessListing.BusinessListing;
import theacreage.Classified.Classified;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by VirtualStorageWorks on 8/27/2014.
 */
@Entity
@Table(name="users")
public class User {


    public User(){}

    public User(User user) {
        this.id = user.getId();
        this.dateJoined = user.getDateJoined();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.enabled = user.isEnabled();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.lastLogin = user.getLastLogin();
    }

    public User(String username, String password, String email){
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    @Id
    @GeneratedValue
    private int id;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required.")
    private String password;

    @Email(message = "Please provide a valid email address.")
    @NotEmpty(message = "Email is required.")
    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private boolean enabled;

    @Column(name="date_joined")
    private Calendar dateJoined;

    @Column(name="last_login")
    private Calendar lastLogin;

    @OneToMany(mappedBy = "user")
    private Set<BusinessListing> businessListings;

    @OneToMany(mappedBy = "user")
    private Set<Classified> classifieds;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Calendar getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Calendar dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Calendar getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Calendar lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<BusinessListing> getBusinessListings() {
        return businessListings;
    }

    public void setBusinessListings(Set<BusinessListing> businessListings) {
        this.businessListings = businessListings;
    }

    public Set<Classified> getClassifieds() {
        return classifieds;
    }

    public void setClassifieds(Set<Classified> classifieds) {
        this.classifieds = classifieds;
    }
}
