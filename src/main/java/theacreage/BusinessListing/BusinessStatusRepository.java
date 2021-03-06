package theacreage.BusinessListing;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wptrs on 8/26/2014.
 */
public interface BusinessStatusRepository extends JpaRepository<BusinessStatus, Integer>{

    public BusinessStatus findByStatusName(String statusName);
}
