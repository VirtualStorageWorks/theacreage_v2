package theacreage.BusinessListing;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by VirtualStorageWorks on 9/1/2014.
 */
@Entity
@Table(name = "business_addresses")
public class BusinessAddress {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "business_address")
    private String address;

    @Column(name = "business_city")
    private String city;

    @Column(name = "business_state")
    private String state;

    @Column(name = "business_zip")
    private String zip;

    @Column(name = "business_address_status")
    private boolean status;

    @Column(name = "date_created")
    private Calendar dateCreated;

    @Column(name = "date_updated")
    private Calendar dateUpdated;

    @ManyToOne
    private BusinessListing businessListing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Calendar getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Calendar dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Calendar getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Calendar dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public BusinessListing getBusinessListing() {
        return businessListing;
    }

    public void setBusinessListing(BusinessListing businessListing) {
        this.businessListing = businessListing;
    }
}
