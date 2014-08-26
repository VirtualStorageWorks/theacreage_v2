package theacreage.BusinessListing;

import theacreage.User.User;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wptrs on 8/26/2014.
 */

@Entity
@Table(name="business_listing")
public class BusinessListing {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private User user;
    @Column(name="business_name")
    private String businessName;
    @ManyToOne
    private BusinessStatus businessStatus;
    @Column(name="date_created")
    private Date dateCreated;
    @Column(name="date_updated")
    private Date dateUpdated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public BusinessStatus getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(BusinessStatus businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
