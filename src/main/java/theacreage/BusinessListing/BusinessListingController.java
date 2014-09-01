package theacreage.BusinessListing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import java.util.Calendar;
import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
@Controller
public class BusinessListingController {
    @Autowired
    private BusinessListingRepository businessListingRepository;

    @Autowired
    private BusinessAddressRepository businessAddressRepository;

    @RequestMapping("/businessdirectory")
    public String showBusinessListings(Model model){
        List<BusinessListing> businessListingList = businessListingRepository.findAll();
        model.addAttribute("listOfBusinesses", businessListingList);
        return "businessListings";
    }

    @RequestMapping("/business/{id}")
    public String showBusinessListingDetails(@PathVariable("id") int id, @ModelAttribute BusinessListing businessListing, Model model){
        businessListing = businessListingRepository.getOne(id);
        model.addAttribute("businessListing", businessListing);
        return "businessListingDetails";
    }

    @RequestMapping(value = "/business/create", method = RequestMethod.GET)
    public String createBusinessListingForm(Model model){
        return "createbusinesslisting";
    }

    @PreAuthorize("#businesslisting.user == authentication.name")
    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public String createBusinessListing(@Valid BusinessListing businessListing, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "createbusinesslisting";
        }
        businessListing.setDateCreated(Calendar.getInstance());
        businessListing.setDateUpdated(Calendar.getInstance());
        businessListing.setBusinessStatus(new BusinessStatus());
        businessListing.setUser((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        businessListingRepository.save(businessListing);

        redirect.addFlashAttribute("globalMessage", "Successfully created Business Listing!");
        return "redirect:/account/";
    }
}
