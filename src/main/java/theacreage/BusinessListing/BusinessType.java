package theacreage.BusinessListing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wpmwm on 9/2/2014.
 */
@Entity
public class BusinessType {

    public BusinessType(){}

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "type")
    private String businessType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }
}
