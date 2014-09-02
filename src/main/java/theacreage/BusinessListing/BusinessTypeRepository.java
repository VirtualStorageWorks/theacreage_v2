package theacreage.BusinessListing;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by wpmwm on 9/2/2014.
 */
public interface BusinessTypeRepository extends JpaRepository<BusinessType, Integer>{

    public BusinessType findByBusinessType(String businessType);
}
