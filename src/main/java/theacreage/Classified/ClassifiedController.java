package theacreage.Classified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.Security.UserRepositoryUserDetailsService;
import theacreage.User.User;
import theacreage.User.UserRepository;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
@Controller
public class ClassifiedController {
    @Autowired
    private ClassifiedRepository classifiedRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/classifieds")
    public String showClassifiedListings(Model model){
        List<Classified> classifiedList = classifiedRepository.findAll();
        model.addAttribute("listOfClassifieds", classifiedList);
        return "classifieds";
    }

    @RequestMapping("/classified/{id}")
    public String showClassifiedListingDetail(@PathVariable("id") int id, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object myUser = (auth != null) ? auth.getPrincipal() : null;
        User user = new User();
        if (myUser instanceof User) {
            user = (User) myUser;
            model.addAttribute("CurrentUser", user);
        }
        Classified classified = classifiedRepository.findOne(id);
        model.addAttribute(classified);
        return "classified";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/classified/create", method = RequestMethod.GET)
    public String createClassifiedForm(){
        return "createclassifiedlisting";
    }

    @RequestMapping(value = "/classified/create", method = RequestMethod.POST)
    public String createClassified(@Valid Classified classified, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "createclassifiedlisting";
        }

        classified.setUser(userRepository.findByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        classified.setDatePosted(Calendar.getInstance());

        classified.setDateModified(Calendar.getInstance());

        classifiedRepository.save(classified);

        return "redirect: /account";
    }

    @RequestMapping(value="/classified/{id}/update", method = RequestMethod.POST)
    public String updateClassified(@Valid Classified classified, BindingResult result){
        if(result.hasErrors()){
            return "/classified/"+classified.getId();
        }
        Classified classifiedToUpdate = classifiedRepository.findOne(classified.getId());
        classifiedToUpdate.setTitle(classified.getTitle());
        classifiedToUpdate.setBody(classified.getBody());
        classifiedToUpdate.setPhoneNumber(classified.getPhoneNumber());
        classifiedToUpdate.setCellPhone(classified.getCellPhone());
        classifiedToUpdate.setEmail(classified.getEmail());
        classifiedToUpdate.setCity(classified.getCity());
        classifiedToUpdate.setState(classified.getState());
        classifiedToUpdate.setZip(classified.getZip());
        classifiedToUpdate.setLatitude(classified.getLatitude());
        classifiedToUpdate.setLongitude(classified.getLongitude());
        classifiedToUpdate.setTitle(classified.getTitle());
        classifiedToUpdate.setAddress(classified.getAddress());
        classifiedToUpdate.setDateModified(Calendar.getInstance());

        classifiedRepository.save(classifiedToUpdate);
        return "/classified/"+classifiedToUpdate.getId();

    }

}
