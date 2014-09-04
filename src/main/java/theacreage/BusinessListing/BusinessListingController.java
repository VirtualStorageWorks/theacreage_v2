package theacreage.BusinessListing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.User.User;
import theacreage.User.UserRepository;

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
    private UserRepository userRepository;

    @Autowired
    private BusinessAddressRepository businessAddressRepository;

    @RequestMapping("/businessdirectory")
    public String businessesList(Model model){
        List<BusinessListing> businessListingList = businessListingRepository.findAllBusinessListings();
        model.addAttribute("listOfBusinesses", businessListingList);
        return "businesses";
    }

    @RequestMapping("/business/{businessName}")
    public String businessDetails(@PathVariable("businessName") String businessName, Model model){
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
    public String createForm(Model model){
        return "createbusinesslisting";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/business/create", method = RequestMethod.POST)
    public String create(@Valid BusinessListing businessListing, @Valid BusinessAddress businessAddress, BindingResult result, RedirectAttributes redirect){
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

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/business/{businessName}/update")
    public String update(@RequestParam("status") String status, @PathVariable("businessName") String businessName, @Valid BusinessListing businessListing, @Valid BusinessAddress businessAddress, BindingResult result){

        BusinessListing businessListingToUpdate = businessListingRepository.findByBusinessName(businessName);
        User currentUser = userRepository.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        Set<BusinessAddress> ba = businessListingToUpdate.getBusinessAddresses();
        List<BusinessAddress> businessAddressList = new ArrayList<BusinessAddress>();
        for(BusinessAddress ba1 : ba){
            businessAddressList.add(ba1);
        }
        if(businessListingToUpdate.getUser().getUsername().equals(currentUser.getUsername())) {
            if (result.hasErrors()) {
                return "/business/" + businessListing.getBusinessName();
            }
            businessAddressList.get(0).setAddress(businessAddress.getAddress());
            businessAddressList.get(0).setCity(businessAddress.getCity());
            businessAddressList.get(0).setState(businessAddress.getState());
            businessAddressList.get(0).setZip(businessAddress.getZip());
            businessAddressList.get(0).setDateUpdated(Calendar.getInstance());
            Set<BusinessAddress> businessAddresses = new HashSet<BusinessAddress>();
            businessAddresses.add( businessAddressList.get(0));
            businessListingToUpdate.setBusinessAddresses(businessAddresses);
            businessListingToUpdate.setBusinessName(businessListing.getBusinessName());
            if (status == "Inactive") {
                businessListingToUpdate.setBusinessStatus(businessStatusRepository.findByStatusName(status));
            }
            businessListingToUpdate.setDateUpdated(Calendar.getInstance());
            businessListingRepository.save(businessListingToUpdate);
        }else{
            return "redirect:/business/"+businessName;
        }

        return "redirect:/business/"+businessName;
    }
}
