package theacreage.Classified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.Security.UserRepositoryUserDetailsService;
import theacreage.User.User;

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
    private UserRepositoryUserDetailsService userRepositoryUserDetailsService;

    @RequestMapping("/classifieds")
    public String showClassifiedListings(Model model){
        List<Classified> classifiedList = classifiedRepository.findAll();
        model.addAttribute("listOfClassifieds", classifiedList);
        return "classifieds";
    }

    @RequestMapping("/classified/{id}")
    public String showClassifiedListingDetail(@PathVariable("id") int id, Model model){
        Classified classified = classifiedRepository.findOne(id);
        model.addAttribute(classified);
        return "classified";
    }

    @RequestMapping(value = "/classified/create", method = RequestMethod.GET)
    public String createClassifiedForm(){
        return "createclassified";
    }

    @PreAuthorize("#classified.user = authentication.name")
    @RequestMapping(value = "/classified/create", method = RequestMethod.POST)
    public String createClassified(@Valid Classified classified, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "createclassified";
        }
        User user = userRepositoryUserDetailsService.getCurrentUser();
        classified.setUser(user);
        classified.setDatePosted(Calendar.getInstance());
        classified.setDateModified(Calendar.getInstance());

        return "redirect: /account/{id}";
    }
}
