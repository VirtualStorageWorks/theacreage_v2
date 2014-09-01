package theacreage.BusinessListing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by VirtualStorageWorks on 9/1/2014.
 */
@Repository
public interface BusinessAddressRepository extends JpaRepository<BusinessAddress, Integer>{

}
