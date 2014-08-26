package theacreage.BusinessListing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
public class BusinessListingController {
    @Autowired
    private BusinessListingRepository businessListingRepository;
    @RequestMapping("/BusinessListings")
    public String showBusinessListings(Model model){
        List<BusinessListing> businessListingList = businessListingRepository.findAll();
        model.addAttribute("listOfBusinesses", businessListingList);
        return "businessListings";
    }
}
