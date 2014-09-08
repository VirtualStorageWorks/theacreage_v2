package theacreage.Classified;
/*

import theacreage.User.User;
*/

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Email;
import theacreage.User.User;

import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Set;

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

    @Size(min = 10, max = 10)
    @NotNull
    @Column(name = "cell_phone")
    private String cellPhone;

    @Email
    @NotNull
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String latitude;
    private String longitude;


    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classified", fetch = FetchType.LAZY)
    private Set<ClassifiedPicture> classifiedPictures;

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

    public Set<ClassifiedPicture> getClassifiedPictures() {
        return classifiedPictures;
    }

    public void setClassifiedPictures(Set<ClassifiedPicture> classifiedPictures) {
        this.classifiedPictures = classifiedPictures;
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
