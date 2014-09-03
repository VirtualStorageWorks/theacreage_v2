package theacreage.BusinessListing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
public interface BusinessListingRepository extends JpaRepository<BusinessListing, Integer>{

    BusinessListing findByBusinessName(String businessName);

    @Query("SELECT bl FROM BusinessListing bl join fetch bl.businessAddresses ba where ba.businessListing = bl")
    List<BusinessListing> findAllBusinessListings();

}
