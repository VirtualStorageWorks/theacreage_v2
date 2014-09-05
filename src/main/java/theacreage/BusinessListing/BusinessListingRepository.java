package theacreage.BusinessListing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
public interface BusinessListingRepository extends JpaRepository<BusinessListing, Integer>{

    BusinessListing findByBusinessName(String businessName);

    @Query("SELECT bl FROM BusinessListing bl left join fetch bl.businessAddresses ba where ba.status = 1")
    List<BusinessListing> findAllBusinessListings();

}
