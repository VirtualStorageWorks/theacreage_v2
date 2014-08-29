package theacreage.BusinessListing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
public class BusinessListingController {
    @Autowired
    private BusinessListingRepository businessListingRepository;

    @RequestMapping("/businesslistings")
    public String showBusinessListings(Model model){
        List<BusinessListing> businessListingList = businessListingRepository.findAll();
        model.addAttribute("listOfBusinesses", businessListingList);
        return "businessListings";
    }

    @RequestMapping("businesslisting/{id}")
    public String showBusinessListingDetails(@PathVariable("id") int id, @ModelAttribute BusinessListing businessListing, Model model){
        businessListing = businessListingRepository.getOne(id);
        model.addAttribute("businessListing", businessListing);
        return "businessListingDetails";
    }
}
