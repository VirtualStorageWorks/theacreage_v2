package theacreage.Classified;

import theacreage.User.User;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wptrs on 8/26/2014.
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
    private String contactphonenumber;
    private String contactcellphone;
    private String contactemail;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String latitude;
    private String longitude;
    private Date postedDate;
    private Date modifiedDate;

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

    public String getContactphonenumber() {
        return contactphonenumber;
    }

    public void setContactphonenumber(String contactphonenumber) {
        this.contactphonenumber = contactphonenumber;
    }

    public String getContactcellphone() {
        return contactcellphone;
    }

    public void setContactcellphone(String contactcellphone) {
        this.contactcellphone = contactcellphone;
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
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

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
