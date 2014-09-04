package theacreage.BusinessListing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.User.User;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
public class BusinessListingController {
    @Autowired
    private BusinessListingRepository businessListingRepository;

    @Autowired
    private BusinessStatusRepository businessStatusRepository;

    @Autowired
    private BusinessTypeRepository businessTypeRepository;

    @Autowired
    private BusinessAddressRepository businessAddressRepository;

    @RequestMapping("/businessdirectory")
    public String showBusinessListings(Model model){
        List<BusinessListing> businessListingList = businessListingRepository.findAllBusinessListings();
        model.addAttribute("listOfBusinesses", businessListingList);
        return "businesses";
    }

    @RequestMapping("/business/{businessName}")
    public String showBusinessListingDetails(@PathVariable("businessName") String businessName, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;
        User user = new User();
        if (myUser instanceof User) {
            user = (User) myUser;
            model.addAttribute("CurrentUser", user);
        }
        BusinessListing businessListing = businessListingRepository.findByBusinessName(businessName);
        model.addAttribute("businessListing", businessListing);
        return "business";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/business/create", method = RequestMethod.GET)
    public String createBusinessListingForm(Model model){
        return "createbusinesslisting";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public String createBusinessListing(@Valid BusinessListing businessListing, @Valid BusinessAddress businessAddress, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "createbusinesslisting";
        }
        businessListing.setDateCreated(Calendar.getInstance());
        businessListing.setDateUpdated(Calendar.getInstance());
        businessListing.setBusinessStatus(businessStatusRepository.findByStatusName("Active"));
        businessListing.setBusinessType(businessTypeRepository.findByBusinessType("Farm Supply"));

        Set<BusinessAddress> businessAddresses = new HashSet<BusinessAddress>();
        businessAddress.setBusinessListing(businessListing);
        businessAddresses.add(businessAddress);
        businessListing.setBusinessAddresses(businessAddresses);
        businessListing.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        businessListingRepository.save(businessListing);

        redirect.addFlashAttribute("globalMessage", "Successfully created Business Listing!");
        return "redirect:/account/";
    }
}
