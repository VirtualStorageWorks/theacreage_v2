package theacreage.BusinessListing;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by wptrs on 8/26/2014.
 */
@Entity
@Table(name="business_status")
public class BusinessStatus {
    @Id
    @GeneratedValue
    private int id;
    @Column(name="status_name")
    private String statusName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
