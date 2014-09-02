package theacreage.Classified;
/*

import theacreage.User.User;
*/

import theacreage.User.User;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
@Entity
@Table(name="classified")
public class Classified {
    @Id
    @GeneratedValue
    private int Id;

    @ManyToOne
    private User user;

    private String title;
    private String body;

    @Column(name = "phone")
    private String phoneNumber;

    @Column(name = "cell_phone")
    private String cellPhone;

    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String latitude;
    private String longitude;

    @Column(name = "date_posted")
    private Calendar datePosted;

    @Column(name = "date_modified")
    private Calendar dateModified;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Calendar getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Calendar datePosted) {
        this.datePosted = datePosted;
    }

    public Calendar getDateModified() {
        return dateModified;
    }

    public void setDateModified(Calendar dateModified) {
        this.dateModified = dateModified;
    }
}
