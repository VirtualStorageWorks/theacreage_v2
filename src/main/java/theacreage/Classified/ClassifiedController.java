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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import theacreage.Security.UserRepositoryUserDetailsService;
import theacreage.User.User;
import theacreage.User.UserRepository;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
@Controller
public class ClassifiedController implements ServletContextAware{
    private ServletContext servletContext;
    @Autowired
    private ClassifiedRepository classifiedRepository;

    @Autowired
    private ClassifiedPictureRespository classifiedPictureRespository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/classifieds")
    public String classifiedsList(Model model){
        List<Classified> classifiedList = classifiedRepository.findAllClassifieds();
        model.addAttribute("listOfClassifieds", classifiedList);
        return "classifieds";
    }

    @RequestMapping("/classified/{id}")
    public String classifiedDetail(@PathVariable("id") int id, Model model){
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

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private void validateImage(MultipartFile image) {
        if (!image.getContentType().equals("image/jpeg") || !image.getContentType().equals("image/jpg") || !image.getContentType().equals("image/png")) {
            throw new RuntimeException("Only JPEG and PNG images are accepted");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/classified/create", method = RequestMethod.GET)
    public String createForm(){
        return "createclassifiedlisting";
    }

    @RequestMapping(value = "/classified/create", method = RequestMethod.POST)
    public String create(@RequestParam("file") MultipartFile[] files, @RequestParam("name") String name, @Valid Classified classified, BindingResult result, RedirectAttributes redirect){
        if(result.hasErrors()){
            return "createclassifiedlisting";
        }

        classified.setUser(userRepository.findByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername()));
        classified.setDatePosted(Calendar.getInstance());

        classified.setDateModified(Calendar.getInstance());
        String fileName = "";
        String filePath = "";
            if (files.length > 0) {
                String message = "";
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    //validateImage(file);
                    try {
                        byte[] bytes = file.getBytes();

                        String fileExtension = file.getContentType().substring(file.getContentType().lastIndexOf("/") + 1);
                        // Creating the directory to store file
                        File dir = new File(servletContext.getRealPath("/") + File.separator + "images");
                        if (!dir.exists())
                            dir.mkdirs();

                        // Create the file on server
                        fileName = classified.getUser().getUsername()+"_Classified_"+classified.getTitle()+ (i+1) + "." + fileExtension;
                        filePath = dir.getAbsolutePath() + File.separator + fileName;
                        File serverFile = new File(filePath);
                        BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(serverFile));
                        stream.write(bytes);
                        stream.close();

                        message = message + "You successfully uploaded file=" + name
                                + "<br />";
                    } catch (Exception e) {
                        return "You failed to upload " + name + " => " + e.getMessage();
                    }
                }
            }
        ClassifiedPicture classifiedPicture = new ClassifiedPicture();
        classifiedPicture.setDateAdded(Calendar.getInstance());
        classifiedPicture.setClassified(classified);
        classifiedPicture.setFileName(fileName);
        classifiedPicture.setFilePath(filePath);

        Set<ClassifiedPicture> classifiedPictures = new HashSet<ClassifiedPicture>();

        classifiedPictures.add(classifiedPicture);
        classified.setClassifiedPictures(classifiedPictures);
        classifiedRepository.save(classified);

        return "redirect:/account";
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value="/classified/{id}/update", method = RequestMethod.POST)
    public String update(@Valid Classified classified, BindingResult result){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Classified classifiedToUpdate = classifiedRepository.findOne(classified.getId());
        if(classifiedToUpdate.getUser().getUsername().equals(user.getUsername())) {
            if (result.hasErrors()) {
                return "/classified/" + classified.getId() + "/update/";
            }
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
            return "redirect:/classified/" + classifiedToUpdate.getId();
        }else{
            return "redirect:/classified/"+classified.getId();
        }
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/classified/{id}/delete")
    public String delete(@PathVariable("id") int id){
        Classified classified = classifiedRepository.findOne(id);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(classified.getUser().getUsername().equals(user.getUsername())) {

            if (classified.getClassifiedPictures().size() > 0) {
                classifiedPictureRespository.delete(classified.getClassifiedPictures());
            }
            classifiedRepository.delete(id);
        }else{
            return "redirect:/classified/"+classified.getId();
        }
        return "redirect:/account";
    }
}
