package theacreage.BusinessListing;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import theacreage.User.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(name="business_listing_status",
            joinColumns = {@JoinColumn(name="business_listing_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="business_status_id", referencedColumnName="id")})
    private BusinessStatus businessStatus;

    @Column(name="date_created")
    private Calendar dateCreated;

    @Column(name="date_updated")
    private Calendar dateUpdated;

    @Fetch(FetchMode.JOIN)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessListing", fetch = FetchType.LAZY)
    private Set<BusinessAddress> businessAddresses = new HashSet<BusinessAddress>(0);

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "business_listing_type",
            joinColumns = {@JoinColumn(name="business_listing_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "business_type_id", referencedColumnName = "id")})
    private BusinessType businessType;

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

    public Set<BusinessAddress> getBusinessAddresses() {
        return businessAddresses;
    }

    public void setBusinessAddresses(Set<BusinessAddress> businessAddresses) {
        this.businessAddresses = businessAddresses;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
}
