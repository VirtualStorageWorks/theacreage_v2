package theacreage.Classified;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
public interface ClassifiedPictureRespository extends JpaRepository<ClassifiedPicture, Integer>{
}
