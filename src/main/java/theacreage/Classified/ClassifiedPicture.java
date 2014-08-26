package theacreage.Classified;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by wptrs on 8/26/2014.
 */
@Entity
@Table(name="classified_picture")
public class ClassifiedPicture {
    @Id
    @GeneratedValue
    private int id;
    private String fileName;
    private String filePath;
    private Date addedDate;

    @ManyToOne
    private Classified classified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setClassified(Classified classified) {
        this.classified = classified;
    }

    public String getFilePath() {

        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }
}
