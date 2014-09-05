package theacreage.Classified;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by wptrs on 8/26/2014.
 */
public interface ClassifiedRepository extends JpaRepository<Classified, Integer>{

    @Query("SELECT c FROM Classified c left join fetch c.classifiedPictures cp")
    List<Classified> findAllClassifieds();

}
