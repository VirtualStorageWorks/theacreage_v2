package theacreage.Classified;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by VirtualStorageWorks on 8/26/2014.
 */
@Entity
@Table(name="classified_picture")
public class ClassifiedPicture {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name="file_path")
    private String filePath;

    @Column(name = "date_added")
    private Calendar dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classified_id")
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Classified getClassified() {
        return classified;
    }

    public void setClassified(Classified classified) {
        this.classified = classified;
    }
}
