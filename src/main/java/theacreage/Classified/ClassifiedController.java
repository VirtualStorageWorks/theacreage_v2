package theacreage.Classified;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import theacreage.Classified.Classified;
import theacreage.BusinessListing.BusinessListingRepository;

import java.util.List;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
@Controller
public class ClassifiedController {
    @Autowired
    private ClassifiedRepository classifiedRepository;
    @RequestMapping("/classifieds")
    public String showClassifiedListings(Model model){
        List<Classified> classifiedList = classifiedRepository.findAll();
        model.addAttribute("listOfClassifieds", classifiedList);
        return "classifieds";
    }

    @RequestMapping("/classifieds/{id}")
    public String showClassifiedListingDetail(@PathVariable("id") int id, Model model){
        Classified classified = classifiedRepository.findOne(id);
        model.addAttribute(classified);
        return "classified";
    }
}
